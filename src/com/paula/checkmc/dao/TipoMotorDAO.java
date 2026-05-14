package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class TipoMotorDAO {

    private static final Logger logger = LogManager.getLogger(TipoMotorDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT tm.id, tm.name ");
        sb.append(" FROM type_engine tm ");

        BASE_SELECT = sb.toString();
    }

    public List<TipoMotor> findAll(Connection c) {

        logger.debug("Listando tipos motor");

        List<TipoMotor> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" ORDER BY tm.name ");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

            logger.debug("Tipos motor encontrados: {}", lista.size());

        } catch (Exception e) {

            logger.error("Error listando tipos motor", e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public TipoMotor findById(Connection c, Long id) {

        logger.debug("Buscando tipo motor por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            sql.append(" WHERE tm.id = ? ");

            ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                TipoMotor tipoMotor = loadNext(rs);

                logger.debug("Tipo motor encontrado: {}", tipoMotor);

                return tipoMotor;
            }

        } catch (Exception e) {

            logger.error("Error buscando tipo motor id: {}", id, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return null;
    }

    private TipoMotor loadNext(ResultSet rs) throws Exception {

        int i = 1;

        TipoMotor tm = new TipoMotor();

        tm.setId(rs.getLong(i++));
        tm.setNombre(rs.getString(i++));

        return tm;
    }
}