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

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT tf.id, tf.name ");
        sb.append(" FROM type_fuel tf ");

        BASE_SELECT = sb.toString();
    }

    public List<TipoCombustible> findAll(Connection c) {

        logger.debug("Listando tipos combustible");

        List<TipoCombustible> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY tf.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug("Tipos combustible encontrados: {}", lista.size());

        } catch (Exception e) {

            logger.error("Error listando tipos combustible", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public TipoCombustible findById(Connection c, Long id) {

        logger.debug("Buscando tipo combustible por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" WHERE tf.id = ? ");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                TipoCombustible tipoCombustible = loadNext(rs);

                logger.debug("Tipo combustible encontrado: {}", tipoCombustible);

                return tipoCombustible;
            }

        } catch (Exception e) {

            logger.error("Error buscando tipo combustible id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    private TipoCombustible loadNext(ResultSet rs) throws Exception {

        int i = 1;

        TipoCombustible tc = new TipoCombustible();

        tc.setId(rs.getLong(i++));
        tc.setNombre(rs.getString(i++));

        return tc;
    }
}