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
	private OrdenTrabajoDAO dao = new OrdenTrabajoDAO();

    private static final String BASE_SELECT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT id, diagnosis, start_date, end_date, ");
        sb.append(" quotation_id, car_id ");
        sb.append(" FROM work_order ");
        BASE_SELECT = sb.toString();
    }

   

    public OrdenTrabajo findById(Long id) {

        logger.debug("Buscando orden trabajo id: {}", id);

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE id = ?")) {

            DAOUtils.setParameters(ps, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? loadNext(rs) : null;

        } catch (Exception e) {
            logger.error("Error en findById: {}", id, e);
        }

        return null;
    }

    public Results<OrdenTrabajoDTO> findByCriteria(OrdenTrabajoCriteria cr, int from, int pageSize) {

        logger.info("criteria: {}", cr);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<OrdenTrabajoDTO> results = new Results<>();

        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);
            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getPresupuestoId() != null) {
                condiciones.add(" quotation_id = ? ");
                parametros.add(cr.getPresupuestoId());
            }

            if (cr.getCocheId() != null) {
                condiciones.add(" car_id = ? ");
                parametros.add(cr.getCocheId());
            }

            if (cr.getFechaInicioDesde() != null) {
                condiciones.add(" start_date >= ? ");
                parametros.add(cr.getFechaInicioDesde());
            }

            if (cr.getFechaInicioHasta() != null) {
                condiciones.add(" start_date <= ? ");
                parametros.add(cr.getFechaInicioHasta());
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY ").append(cr.getOrderBy())
               .append(cr.isAscDesc() ? " ASC " : " DESC ")
               .append(" LIMIT ? OFFSET ? ");

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
            results.setTotal(page.size());

            return results;

        } catch (Exception e) {
            logger.error("Error en findByCriteria: {}", cr, e);
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return results;
    }

    public Long create(OrdenTrabajo ot) {

        logger.debug("Creando orden trabajo: {}", ot);

        String sql = "INSERT INTO work_order(diagnosis,start_date,end_date,quotation_id,car_id) VALUES(?,?,?,?,?)";

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            DAOUtils.setParameters(ps,
                    ot.getDiagnostico(),
                    ot.getFechaInicio(),
                    ot.getFechaFin(),
                    ot.getPresupuestoId(),
                    ot.getCocheId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                logger.info("Orden creada con id: {}", id);
                return id;
            }

        } catch (Exception e) {
            logger.error("Error creando orden trabajo: {}", ot, e);
        }

        return null;
    }

    public boolean update(OrdenTrabajo ot) {

        logger.debug("Actualizando orden trabajo: {}", ot);

        String sql = "UPDATE work_order SET diagnosis=?, start_date=?, end_date=?, quotation_id=?, car_id=? WHERE id=?";

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            DAOUtils.setParameters(ps,
                    ot.getDiagnostico(),
                    ot.getFechaInicio(),
                    ot.getFechaFin(),
                    ot.getPresupuestoId(),
                    ot.getCocheId(),
                    ot.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error update orden trabajo", e);
        }

        return false;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando orden trabajo id: {}", id);

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM work_order WHERE id=?")) {

            DAOUtils.setParameters(ps, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error delete orden trabajo: {}", id, e);
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