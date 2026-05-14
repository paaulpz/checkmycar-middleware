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

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT r.id, r.name ");
        sb.append(" FROM rol r ");

        BASE_SELECT = sb.toString();
    }

    public List<Rol> findAll(Connection c) {

        logger.debug("Listando roles");

        List<Rol> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY r.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug("Roles encontrados: {}", lista.size());

        } catch (Exception e) {

            logger.error("Error listando roles", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public Rol findById(Connection c, Long id) {

        logger.debug("Buscando rol por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" WHERE r.id = ? ");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                Rol rol = loadNext(rs);

                logger.debug("Rol encontrado: {}", rol);

                return rol;
            }

        } catch (Exception e) {

            logger.error("Error buscando rol id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    private Rol loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Rol r = new Rol();

        r.setId(rs.getLong(i++));
        r.setNombre(rs.getString(i++));

        return r;
    }
}