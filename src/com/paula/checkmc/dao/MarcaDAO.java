package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Marca;
import com.paula.checkmc.util.JDBCUtils;

public class MarcaDAO {

    private static final Logger logger = LogManager.getLogger(MarcaDAO.class);

    public Marca findById(Connection c ,Long id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM brand ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando marca: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);     
        }

        return null;
    }

    public List<Marca> findAll(Connection c) {
    	

        List<Marca> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM brand ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando marcas", e);

        } finally {

            JDBCUtils.close(rs, ps);     
        }

        return lista;
    }

    private Marca loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Marca m = new Marca();

        m.setId(rs.getLong(i++));
        m.setNombre(rs.getString(i++));

        return m;
    }
}