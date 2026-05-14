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

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT g.id, g.name ");
        sb.append(" FROM gender g ");

        BASE_SELECT = sb.toString();
    }

    public GeneroDAO() {

    }

    public Genero findById(Connection c, Long id) {

        logger.debug("Buscando genero por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = c.prepareStatement(BASE_SELECT + " WHERE g.id = ? ");

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                Genero genero = loadNext(rs);

                logger.debug("Genero encontrado: {}", genero);

                return genero;
            }

        } catch (Exception e) {

            logger.error("Error buscando genero id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public List<Genero> findAll(Connection c) {

        logger.debug("Listando generos");

        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Genero> lista = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY g.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug("Generos encontrados: {}", lista.size());

            return lista;

        } catch (Exception e) {

            logger.error("Error listando generos", e);

        } finally {

            JDBCUtils.close(rs, ps);
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