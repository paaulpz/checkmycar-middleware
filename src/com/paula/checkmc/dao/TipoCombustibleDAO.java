package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class TipoCombustibleDAO {

    private static final Logger logger = LogManager.getLogger(TipoCombustibleDAO.class);

    public List<TipoCombustible> findAll() {

        List<TipoCombustible> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name ");
            sql.append("FROM type_fuel ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando tipos combustible", e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return lista;
    }

    private TipoCombustible loadNext(ResultSet rs) throws Exception {

        int i = 1;

        TipoCombustible tc = new TipoCombustible();

        tc.setId(rs.getLong(i++));
        tc.setNombre(rs.getString(i++));

        return tc;
    }
}