package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPresupuestoDAO {

    private static final Logger logger =
            LogManager.getLogger(EstadoPresupuestoDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT qs.id, qs.name ");
        sb.append(" FROM quotation_status qs ");

        BASE_SELECT = sb.toString();
    }


    public EstadoPresupuesto findById(Connection c, Long id) {

        logger.debug("Buscando estado presupuesto por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = c.prepareStatement(BASE_SELECT + " WHERE qs.id = ? ");

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                EstadoPresupuesto estado = loadNext(rs);

                logger.debug("Estado presupuesto encontrado: {}", estado);

                return estado;
            }

        } catch (Exception e) {

            logger.error(
                    "Error buscando estado presupuesto id: {}",
                    id,
                    e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public List<EstadoPresupuesto> findAll(Connection c) {

        logger.debug("Listando estados de presupuesto");

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<EstadoPresupuesto> lista = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY qs.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug(
                    "Estados presupuesto encontrados: {}",
                    lista.size());

            return lista;

        } catch (Exception e) {

            logger.error("Error listando estados presupuesto", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    private EstadoPresupuesto loadNext(ResultSet rs) throws Exception {

        int i = 1;

        EstadoPresupuesto e = new EstadoPresupuesto();

        e.setId(rs.getLong(i++));
        e.setNombre(rs.getString(i++));

        return e;
    }
}