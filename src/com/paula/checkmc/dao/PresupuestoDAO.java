package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Presupuesto;
import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class PresupuestoDAO {

    private static final Logger logger = LogManager.getLogger(PresupuestoDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT id, date, final_price, ");
        sb.append("client_id, quotation_status_id, name ");
        sb.append("FROM quotation ");

        BASE_SELECT = sb.toString();
    }

    public Presupuesto findById(Long id) {

        logger.debug("Buscando presupuesto id: {}", id);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error findById presupuesto: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public Results<PresupuestoDTO> findByCriteria(PresupuestoCriteria cr, int from, int pageSize) {

        logger.info("criteria: {}", cr);

        Connection c = null;
        PreparedStatement ps = null;
        PreparedStatement psCount = null;

        ResultSet rs = null;
        ResultSet rsCount = null;

        Results<PresupuestoDTO> results = new Results<>();

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getClienteId() != null) {

                condiciones.add("client_id=?");
                parametros.add(cr.getClienteId());
            }

            if (cr.getEstadoPresupuestoId() != null) {

                condiciones.add("quotation_status_id=?");
                parametros.add(cr.getEstadoPresupuestoId());
            }

            if (cr.getFechaDesde() != null) {

                condiciones.add("date >= ?");
                parametros.add(cr.getFechaDesde());
            }

            if (cr.getFechaHasta() != null) {

                condiciones.add("date <= ?");
                parametros.add(cr.getFechaHasta());
            }

            if (!condiciones.isEmpty()) {

                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY ");
            sql.append(cr.getOrderBy());
            sql.append(cr.isAscDesc() ? " ASC " : " DESC ");
            sql.append(" LIMIT ? OFFSET ? ");

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(sql.toString());

            int i = 1;

            for (Object param : parametros) {

                ps.setObject(i++, param);
            }

            ps.setInt(i++, pageSize);
            ps.setInt(i++, from - 1);

            rs = ps.executeQuery();

            List<PresupuestoDTO> page = new ArrayList<>();

            while (rs.next()) {

                PresupuestoDTO dto = new PresupuestoDTO();

                dto.setId(rs.getLong("id"));
                dto.setFecha(rs.getDate("date").toLocalDate());
                dto.setPrecioFinal(rs.getDouble("final_price"));
                dto.setClienteId(rs.getLong("client_id"));
                dto.setEstadoPresupuestoId(rs.getLong("quotation_status_id"));
                dto.setNombre(rs.getString("name"));

                page.add(dto);
            }

            results.setPage(page);

            StringBuilder countSql = new StringBuilder();

            countSql.append("SELECT COUNT(*) ");
            countSql.append("FROM quotation ");

            if (!condiciones.isEmpty()) {

                countSql.append("WHERE ");
                countSql.append(String.join(" AND ", condiciones));
            }

            psCount = c.prepareStatement(countSql.toString());

            int j = 1;

            for (Object param : parametros) {

                psCount.setObject(j++, param);
            }

            rsCount = psCount.executeQuery();

            if (rsCount.next()) {

                results.setTotal(rsCount.getInt(1));
            }

        } catch (Exception e) {

            logger.error("Error findByCriteria presupuesto: {}", cr, e);

        } finally {

            DAOUtils.close(rsCount, psCount, null);
            DAOUtils.close(rs, ps, c);
        }

        return results;
    }

    public Long create(Presupuesto p) {

        logger.debug("Creando presupuesto: {}", p);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO quotation ");
            sql.append("(date, final_price, client_id, quotation_status_id, name) ");
            sql.append("VALUES (?,?,?,?,?)");

            ps = c.prepareStatement(
                    sql.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);

            DAOUtils.setParameters(
                    ps,
                    java.sql.Date.valueOf(p.getFecha()),
                    p.getPrecioFinal(),
                    p.getClienteId(),
                    p.getEstadoPresupuestoId(),
                    p.getNombre());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = rs.getLong(1);

                logger.info("Presupuesto creado con id: {}", id);

                return id;
            }

        } catch (Exception e) {

            logger.error("Error creando presupuesto: {}", p, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public boolean update(Presupuesto p) {

        logger.debug("Actualizando presupuesto: {}", p);

        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE quotation SET ");
            sql.append("date=?, final_price=?, client_id=?, ");
            sql.append("quotation_status_id=?, name=? ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(
                    ps,
                    java.sql.Date.valueOf(p.getFecha()),
                    p.getPrecioFinal(),
                    p.getClienteId(),
                    p.getEstadoPresupuestoId(),
                    p.getNombre(),
                    p.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error actualizando presupuesto: {}", p, e);

        } finally {

            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando presupuesto id: {}", id);

        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM quotation ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error eliminando presupuesto: {}", id, e);

        } finally {

            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    private Presupuesto loadNext(ResultSet rs) throws Exception {

        Presupuesto p = new Presupuesto();

        p.setId(rs.getLong("id"));
        p.setFecha(rs.getDate("date").toLocalDate());
        p.setPrecioFinal(rs.getDouble("final_price"));
        p.setClienteId(rs.getLong("client_id"));
        p.setEstadoPresupuestoId(rs.getLong("quotation_status_id"));
        p.setNombre(rs.getString("name"));

        return p;
    }
}