package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.EstadoCitaDAO;
import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.service.EstadoCitaService;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoCitaServiceImpl implements EstadoCitaService {

    private static final Logger logger = LogManager.getLogger(EstadoCitaServiceImpl.class);

    private EstadoCitaDAO estadoCitaDAO = new EstadoCitaDAO();

    @Override
    public EstadoCita findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return estadoCitaDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando estado cita {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<EstadoCita> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return estadoCitaDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando estados cita: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}