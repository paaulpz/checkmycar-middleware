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

        sb.append(" SELECT q.id, q.date, q.final_price, ");
        sb.append(" q.client_id, q.quotation_status_id, ");
        sb.append(" q.name ");
        sb.append(" FROM quotation q ");

        BASE_SELECT = sb.toString();
    }

    public PresupuestoDAO() {

    }

    public Presupuesto findById(Connection c, Long id) {

        logger.debug("Buscando presupuesto por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = c.prepareStatement(BASE_SELECT + " WHERE q.id = ? ");

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                Presupuesto presupuesto = loadNext(rs);

                logger.debug("Presupuesto encontrado: {}", presupuesto);

                return presupuesto;
            }

        } catch (Exception e) {

            logger.error("Error buscando presupuesto id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public Results<PresupuestoDTO> findByCriteria(Connection c, PresupuestoCriteria cr, int from, int pageSize) throws Exception {

        logger.info("criteria: {}", cr);

        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<PresupuestoDTO> results = new Results<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getClienteId() != null) {

                condiciones.add(" q.client_id = ? ");
                parametros.add(cr.getClienteId());
            }

            if (cr.getEstadoPresupuestoId() != null) {

                condiciones.add(" q.quotation_status_id = ? ");
                parametros.add(cr.getEstadoPresupuestoId());
            }

            if (cr.getFechaDesde() != null) {

                condiciones.add(" q.date >= ? ");
                parametros.add(cr.getFechaDesde());
            }

            if (cr.getFechaHasta() != null) {

                condiciones.add(" q.date <= ? ");
                parametros.add(cr.getFechaHasta());
            }

            if (!condiciones.isEmpty()) {

                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            String orderBy = cr.getOrderBy() != null ? cr.getOrderBy() : "q.id";

            sql.append(" ORDER BY ")
               .append(orderBy)
               .append(cr.isAscDesc() ? " ASC " : " DESC ")
               .append(" LIMIT ? OFFSET ? ");

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(
                    sql.toString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

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

            countSql.append(" SELECT COUNT(*) ");
            countSql.append(" FROM quotation q ");

            if (!condiciones.isEmpty()) {

                countSql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            PreparedStatement psCount = c.prepareStatement(countSql.toString());

            int idx = 1;

            for (Object param : parametros) {

                psCount.setObject(idx++, param);
            }

            ResultSet rsCount = psCount.executeQuery();

            if (rsCount.next()) {

                results.setTotal(rsCount.getInt(1));
            }

            return results;

        } catch (Exception e) {

            logger.error("Error en findByCriteria presupuesto: {}", cr, e);

            throw e;

        } finally {

            JDBCUtils.close(rs, ps);
        }
    }

    public Long create(Connection c, Presupuesto p) throws Exception {

        logger.debug("Creando presupuesto: {}", p);

        StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO quotation ");
        sql.append(" (date, final_price, client_id, quotation_status_id, name) ");
        sql.append(" VALUES (?,?,?,?,?) ");

        try {

            PreparedStatement ps = c.prepareStatement(
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

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = rs.getLong(1);

                logger.info("Presupuesto creado con id: {}", id);

                return id;

            } else {

                return null;
            }

        } catch (Exception e) {

            logger.error("Error creando presupuesto: {}", p, e);

            throw e;
        }
    }

    public boolean update(Connection c, Presupuesto p) throws Exception {

        logger.debug("Actualizando presupuesto: {}", p);

        StringBuilder sql = new StringBuilder();

        sql.append(" UPDATE quotation SET ");
        sql.append(" date=?, final_price=?, client_id=?, ");
        sql.append(" quotation_status_id=?, name=? ");
        sql.append(" WHERE id=? ");

        try (PreparedStatement ps = c.prepareStatement(sql.toString())) {

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

            throw e;
        }
    }

    public boolean delete(Connection c, Long id) throws Exception {

        logger.warn("Eliminando presupuesto id: {}", id);

        try (PreparedStatement ps = c.prepareStatement("DELETE FROM quotation WHERE id=?")) {

            ps.setLong(1, id);

            boolean eliminado = ps.executeUpdate() > 0;

            logger.warn("Presupuesto {} {} {}", id, eliminado ? "" : "NO", "eliminado.");

            return eliminado;

        } catch (Exception e) {

            logger.error("Error eliminando presupuesto id: {}", id, e);

            throw e;
        }
    }

    private Presupuesto loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Presupuesto p = new Presupuesto();

        p.setId(rs.getLong(i++));
        p.setFecha(rs.getDate(i++).toLocalDate());
        p.setPrecioFinal(rs.getDouble(i++));
        p.setClienteId(rs.getLong(i++));
        p.setEstadoPresupuestoId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));

        return p;
    }
}