package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.util.JDBCUtils;

public class RolDAO {

    private static final Logger logger = LogManager.getLogger(RolDAO.class);

    public List<Rol> findAll(Connection c) {
    	

        List<Rol> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM rol ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando roles", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    private Rol loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Rol r = new Rol();

        r.setId(rs.getLong(i++));
        r.setNombre(rs.getString(i++));

        return r;
    }
}