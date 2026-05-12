package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class ModeloDAO {

    private static final Logger logger = LogManager.getLogger(ModeloDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT m.id, m.name, m.brand_id ");
        sb.append("FROM model m ");

        BASE_SELECT = sb.toString();
    }

    public List<Modelo> findByMarca(Long marcaId) {

        List<Modelo> lista = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append("WHERE m.brand_id=? ");
            sql.append("ORDER BY m.name");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, marcaId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando modelos por marca: {}", marcaId, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return lista;
    }

    private Modelo loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Modelo m = new Modelo();

        m.setId(rs.getLong(i++));
        m.setNombre(rs.getString(i++));
        m.setMarcaId(rs.getLong(i++));

        return m;
    }
}