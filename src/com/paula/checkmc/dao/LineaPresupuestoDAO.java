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

        sb.append("SELECT id, units, unit_price, final_price, ");
        sb.append("part_id, quotation_id ");
        sb.append("FROM quotation_line ");

        BASE_SELECT = sb.toString();
    }

    public List<LineaPresupuesto> findByPresupuesto(Long presupuestoId) {

        logger.debug("Buscando lineas presupuesto id: {}", presupuestoId);

        List<LineaPresupuesto> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append("WHERE quotation_id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, presupuestoId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error findByPresupuesto: {}", presupuestoId, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return lista;
    }

    public Long create(LineaPresupuesto lp) {

        logger.debug("Creando linea presupuesto: {}", lp);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO quotation_line ");
            sql.append("(units, unit_price, final_price, part_id, quotation_id) ");
            sql.append("VALUES (?,?,?,?,?)");

            ps = c.prepareStatement(
                    sql.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS);

            DAOUtils.setParameters(
                    ps,
                    lp.getUnidades(),
                    lp.getPrecioUnitario(),
                    lp.getPrecioFinal(),
                    lp.getPiezaId(),
                    lp.getPresupuestoId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = rs.getLong(1);

                logger.info("Linea creada con id: {}", id);

                return id;
            }

        } catch (Exception e) {

            logger.error("Error creando linea presupuesto: {}", lp, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando linea presupuesto id: {}", id);

        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = DAOUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM quotation_line ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error eliminando linea presupuesto: {}", id, e);

        } finally {

            DAOUtils.close(null, ps, c);
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