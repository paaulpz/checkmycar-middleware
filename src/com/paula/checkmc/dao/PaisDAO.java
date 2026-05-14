package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Pais;
import com.paula.checkmc.util.JDBCUtils;

public class PaisDAO {

    private static final Logger logger = LogManager.getLogger(PaisDAO.class);

    public Pais findById(Connection c,Long id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM country ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando pais: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public List<Pais> findAll(	Connection c) {

        List<Pais> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM country ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando paises", e);

        } finally {

            JDBCUtils.close(rs, ps);
		}

        return lista;
    }

    private Pais loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Pais p = new Pais();

        p.setId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));

        return p;
    }
}