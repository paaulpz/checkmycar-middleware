package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class TipoTransmisionDAO {

    private static final Logger logger = LogManager.getLogger(TipoTransmisionDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT tt.id, tt.name ");
        sb.append(" FROM transmission_type tt ");

        BASE_SELECT = sb.toString();
    }

    public List<TipoTransmision> findAll(Connection c) {

        logger.debug("Listando tipos transmision");

        List<TipoTransmision> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY tt.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug("Tipos transmision encontrados: {}", lista.size());

        } catch (Exception e) {

            logger.error("Error listando tipos transmision", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public TipoTransmision findById(Connection c, Long id) {

        logger.debug("Buscando tipo transmision por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" WHERE tt.id = ? ");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                TipoTransmision tipoTransmision = loadNext(rs);

                logger.debug("Tipo transmision encontrado: {}", tipoTransmision);

                return tipoTransmision;
            }

        } catch (Exception e) {

            logger.error("Error buscando tipo transmision id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    private TipoTransmision loadNext(ResultSet rs) throws Exception {

        int i = 1;

        TipoTransmision tt = new TipoTransmision();

        tt.setId(rs.getLong(i++));
        tt.setNombre(rs.getString(i++));

        return tt;
    }
}