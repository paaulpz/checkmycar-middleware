package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.EstadoPiezaDAO;
import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.service.EstadoPiezaService;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPiezaServiceImpl implements EstadoPiezaService {

    private static final Logger logger = LogManager.getLogger(EstadoPiezaServiceImpl.class);

    private EstadoPiezaDAO estadoPiezaDAO = new EstadoPiezaDAO();

    @Override
    public EstadoPieza findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return estadoPiezaDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando estado pieza {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<EstadoPieza> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return estadoPiezaDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando estados pieza: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}