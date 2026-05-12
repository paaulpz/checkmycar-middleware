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

    private static final Logger logger = LogManager.getLogger(EstadoPresupuestoDAO.class);

    public EstadoPresupuesto findById(Long id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM quotation_status ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando estado presupuesto: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public List<EstadoPresupuesto> findAll() {

        List<EstadoPresupuesto> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM quotation_status ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando estados presupuesto", e);

        } finally {

            DAOUtils.close(rs, ps, c);
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