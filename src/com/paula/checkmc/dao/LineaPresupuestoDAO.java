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
import com.paula.checkmc.util.JDBCUtils;

public class LineaPresupuestoDAO {

    private static final Logger logger =
            LogManager.getLogger(LineaPresupuestoDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ql.id, ql.units, ql.unit_price, ");
        sb.append(" ql.final_price, ql.part_id, ");
        sb.append(" ql.quotation_id ");
        sb.append(" FROM quotation_line ql ");

        BASE_SELECT = sb.toString();
    }

    public LineaPresupuestoDAO() {

    }

    public List<LineaPresupuesto> findByPresupuesto( Connection c, Long presupuestoId) {

        logger.debug(  "Buscando lineas de presupuesto id: {}", presupuestoId);

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<LineaPresupuesto> lista = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" WHERE ql.quotation_id = ? ");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps, presupuestoId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug(
                    "Lineas encontradas para presupuesto {}: {}",
                    presupuestoId,
                    lista.size());

            return lista;

        } catch (Exception e) {

            logger.error(
                    "Error buscando lineas presupuesto id: {}",
                    presupuestoId,
                    e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public Long create(Connection c, LineaPresupuesto lp)
            throws Exception {

        logger.debug("Creando linea presupuesto: {}", lp);

        StringBuilder sql = new StringBuilder();

        sql.append(" INSERT INTO quotation_line ");
        sql.append(" (units, unit_price, final_price, ");
        sql.append(" part_id, quotation_id) ");
        sql.append(" VALUES (?,?,?,?,?) ");

        try {

            PreparedStatement ps = c.prepareStatement(
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

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                Long id = rs.getLong(1);

                logger.info(
                        "Linea presupuesto creada con id: {}",
                        id);

                return id;

            } else {

                return null;
            }

        } catch (Exception e) {

            logger.error(
                    "Error creando linea presupuesto: {}",
                    lp,
                    e);

            throw e;
        }
    }

    public boolean delete(Connection c, Long id)
            throws Exception {

        logger.warn(
                "Eliminando linea presupuesto id: {}",
                id);

        try (PreparedStatement ps =
                     c.prepareStatement(
                             "DELETE FROM quotation_line WHERE id=?")) {

            ps.setLong(1, id);

            boolean eliminado = ps.executeUpdate() > 0;

            logger.warn(
                    "Linea presupuesto {} {} {}",
                    id,
                    eliminado ? "" : "NO",
                    "eliminada.");

            return eliminado;

        } catch (Exception e) {

            logger.error(
                    "Error eliminando linea presupuesto id: {}",
                    id,
                    e);

            throw e;
        }
    }

    private LineaPresupuesto loadNext(ResultSet rs)
            throws Exception {

        int i = 1;

        LineaPresupuesto lp = new LineaPresupuesto();

        lp.setId(rs.getLong(i++));
        lp.setUnidades(rs.getDouble(i++));
        lp.setPrecioUnitario(rs.getDouble(i++));
        lp.setPrecioFinal(rs.getDouble(i++));
        lp.setPiezaId(rs.getLong(i++));
        lp.setPresupuestoId(rs.getLong(i++));

        return lp;
    }
}