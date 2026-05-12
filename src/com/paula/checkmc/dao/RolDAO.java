package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class RolDAO {

    private static final Logger logger = LogManager.getLogger(RolDAO.class);

    public List<Rol> findAll() {

        List<Rol> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

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

            DAOUtils.close(rs, ps, c);
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