package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.OrdenTrabajo;
import com.paula.checkmc.model.OrdenTrabajoCriteria;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class OrdenTrabajoDAO {

    private static final Logger logger = LogManager.getLogger(OrdenTrabajoDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT id, diagnosis, start_date, end_date, ");
        sb.append("quotation_id, car_id ");
        sb.append("FROM work_order ");

        BASE_SELECT = sb.toString();
    }

    public OrdenTrabajo findById(Connection c ,Long id) {

        logger.debug("Buscando orden trabajo id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error findById: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public Results<OrdenTrabajoDTO> findByCriteria(Connection c ,OrdenTrabajoCriteria cr, int from, int pageSize) {

        logger.info("criteria: {}", cr);

        PreparedStatement ps = null;
        PreparedStatement psCount = null;

        ResultSet rs = null;
        ResultSet rsCount = null;

        Results<OrdenTrabajoDTO> results = new Results<>();

        try {


            StringBuilder sql = new StringBuilder(BASE_SELECT);

            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getPresupuestoId() != null) {

                condiciones.add("quotation_id=?");
                parametros.add(cr.getPresupuestoId());
            }

            if (cr.getCocheId() != null) {

                condiciones.add("car_id=?");
                parametros.add(cr.getCocheId());
            }

            if (cr.getFechaInicioDesde() != null) {

                condiciones.add("start_date >= ?");
                parametros.add(cr.getFechaInicioDesde());
            }

            if (cr.getFechaInicioHasta() != null) {

                condiciones.add("start_date <= ?");
                parametros.add(cr.getFechaInicioHasta());
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

            List<OrdenTrabajoDTO> page = new ArrayList<>();

            while (rs.next()) {

                OrdenTrabajoDTO dto = new OrdenTrabajoDTO();

                dto.setId(rs.getLong("id"));
                dto.setDiagnostico(rs.getString("diagnosis"));
                dto.setFechaInicio(rs.getDate("start_date").toLocalDate());

                if (rs.getDate("end_date") != null) {

                    dto.setFechaFin(rs.getDate("end_date").toLocalDate());
                }

                dto.setPresupuestoId(rs.getLong("quotation_id"));
                dto.setCocheId(rs.getLong("car_id"));

                page.add(dto);
            }

            results.setPage(page);

            StringBuilder countSql = new StringBuilder();

            countSql.append("SELECT COUNT(*) ");
            countSql.append("FROM work_order ");

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

            logger.error("Error findByCriteria: {}", cr, e);

        } finally {

            JDBCUtils.close(rsCount, psCount, null);
            JDBCUtils.close(rs, ps);     
        }

        return results;
    }

    public Long create(Connection c ,OrdenTrabajo ot) {

        logger.debug("Creando orden trabajo: {}", ot);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO work_order ");
            sql.append("(diagnosis, start_date, end_date, quotation_id, car_id) ");
            sql.append("VALUES (?,?,?,?,?)");

            ps = c.prepareStatement(
                    sql.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);

            DAOUtils.setParameters(
                    ps,
                    ot.getDiagnostico(),
                    ot.getFechaInicio(),
                    ot.getFechaFin(),
                    ot.getPresupuestoId(),
                    ot.getCocheId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = rs.getLong(1);

                logger.info("Orden creada con id: {}", id);

                return id;
            }

        } catch (Exception e) {

            logger.error("Error creando orden trabajo: {}", ot, e);

        } finally {

            JDBCUtils.close(rs, ps);     
        }

        return null;
    }

    public boolean update(Connection c ,OrdenTrabajo ot) {

        logger.debug("Actualizando orden trabajo: {}", ot);

        PreparedStatement ps = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE work_order SET ");
            sql.append("diagnosis=?, start_date=?, end_date=?, ");
            sql.append("quotation_id=?, car_id=? ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(
                    ps,
                    ot.getDiagnostico(),
                    ot.getFechaInicio(),
                    ot.getFechaFin(),
                    ot.getPresupuestoId(),
                    ot.getCocheId(),
                    ot.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error update orden trabajo", e);

        } finally {

            JDBCUtils.close(null, ps);     
        }

        return false;
    }

    public boolean delete(Connection c ,Long id) {

        logger.warn("Eliminando orden trabajo id: {}", id);

        PreparedStatement ps = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM work_order ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error delete orden trabajo: {}", id, e);

        } finally {

            JDBCUtils.close(null, ps);
        }

        return false;
    }

    private OrdenTrabajo loadNext(ResultSet rs) throws Exception {

        int i = 1;

        OrdenTrabajo ot = new OrdenTrabajo();

        ot.setId(rs.getLong(i++));
        ot.setDiagnostico(rs.getString(i++));
        ot.setFechaInicio(rs.getDate(i++).toLocalDate());

        if (rs.getDate(i) != null) {

            ot.setFechaFin(rs.getDate(i).toLocalDate());
        }

        i++;

        ot.setPresupuestoId(rs.getLong(i++));
        ot.setCocheId(rs.getLong(i++));

        return ot;
    }
}