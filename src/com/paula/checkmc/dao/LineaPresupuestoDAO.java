package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.LineaPresupuesto;
import com.paula.checkmc.util.DAOUtils;

public class LineaPresupuestoDAO {

    private static final Logger logger = LogManager.getLogger(LineaPresupuestoDAO.class);

    private static final String BASE_SELECT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT id, units, unit_price, final_price, ");
        sb.append(" part_id, quotation_id ");
        sb.append(" FROM quotation_line ");
        BASE_SELECT = sb.toString();
    }

    public List<LineaPresupuesto> findByPresupuesto(Long presupuestoId) {

        logger.debug("Buscando lineas de presupuesto id: {}", presupuestoId);

        List<LineaPresupuesto> res = new ArrayList<>();

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE quotation_id = ?")) {

            DAOUtils.setParameters(ps, presupuestoId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                res.add(loadNext(rs));
            }

        } catch (Exception e) {
            logger.error("Error en findByPresupuesto: {}", presupuestoId, e);
        }

        return res;
    }

    public Long create(LineaPresupuesto lp) {

        logger.debug("Creando linea presupuesto: {}", lp);

        String sql = "INSERT INTO quotation_line(units, unit_price, final_price, part_id, quotation_id) VALUES(?,?,?,?,?)";

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            DAOUtils.setParameters(ps,
                    lp.getUnidades(),
                    lp.getPrecioUnitario(),
                    lp.getPrecioFinal(),
                    lp.getPiezaId(),
                    lp.getPresupuestoId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                logger.info("Linea creada con id: {}", id);
                return id;
            }

        } catch (Exception e) {
            logger.error("Error creando linea presupuesto: {}", lp, e);
        }

        return null;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando linea presupuesto id: {}", id);

        try (Connection c = DAOUtils.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM quotation_line WHERE id=?")) {

            DAOUtils.setParameters(ps, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error eliminando linea presupuesto id: {}", id, e);
        }

        return false;
    }

    private LineaPresupuesto loadNext(ResultSet rs) throws Exception {

        LineaPresupuesto lp = new LineaPresupuesto();

        lp.setId(rs.getLong("id"));
        lp.setUnidades(rs.getDouble("units"));
        lp.setPrecioUnitario(rs.getDouble("unit_price"));
        lp.setPrecioFinal(rs.getDouble("final_price"));
        lp.setPiezaId(rs.getLong("part_id"));
        lp.setPresupuestoId(rs.getLong("quotation_id"));

        return lp;
    }
}