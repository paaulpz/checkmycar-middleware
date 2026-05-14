package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.TipoTransmisionDAO;
import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.TipoTransmisionService;
import com.paula.checkmc.util.JDBCUtils;

public class TipoTransmisionServiceImpl implements TipoTransmisionService {

    private static final Logger logger = LogManager.getLogger(TipoTransmisionServiceImpl.class);

    private TipoTransmisionDAO tipoTransmisionDAO = new TipoTransmisionDAO();

    @Override
    public TipoTransmision findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return tipoTransmisionDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando tipo transmision {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<TipoTransmision> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return tipoTransmisionDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando tipos transmision: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}