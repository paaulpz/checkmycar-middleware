package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Genero;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class GeneroDAO {

    private static final Logger logger = LogManager.getLogger(GeneroDAO.class);

    public Genero findById(Long id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM gender ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando genero: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public List<Genero> findAll() {

        List<Genero> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM gender ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando generos", e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return lista;
    }

    private Genero loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Genero g = new Genero();

        g.setId(rs.getLong(i++));
        g.setNombre(rs.getString(i++));

        return g;
    }
}