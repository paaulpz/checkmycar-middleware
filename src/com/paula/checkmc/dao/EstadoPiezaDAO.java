package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPiezaDAO {

    private static final Logger logger = LogManager.getLogger(EstadoPiezaDAO.class);

    public EstadoPieza findById(Connection c, Long id) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM part_status ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando estado pieza: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public List<EstadoPieza> findAll(Connection c) {

        List<EstadoPieza> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM part_status ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando estados pieza", e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return lista;
    }

    private EstadoPieza loadNext(ResultSet rs) throws Exception {

        int i = 1;

        EstadoPieza e = new EstadoPieza();

        e.setId(rs.getLong(i++));
        e.setNombre(rs.getString(i++));

        return e;
    }
}