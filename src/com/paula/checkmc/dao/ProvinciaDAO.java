package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.util.JDBCUtils;

public class ProvinciaDAO {

    private static final Logger logger = LogManager.getLogger(ProvinciaDAO.class);

    public Provincia findById(Connection c ,Long id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, country_id ");
            sql.append("FROM province ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando provincia: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public List<Provincia> findByPais(Connection c ,Long paisId) {

        List<Provincia> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, country_id ");
            sql.append("FROM province ");
            sql.append("WHERE country_id=? ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, paisId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando provincias por pais: {}", paisId, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }
    
  

    private Provincia loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Provincia p = new Provincia();

        p.setId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));
        p.setPaisId(rs.getLong(i++));

        return p;
    }

	
}