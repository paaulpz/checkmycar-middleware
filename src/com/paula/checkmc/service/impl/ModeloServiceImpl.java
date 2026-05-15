package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.ModeloDAO;
import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.service.ModeloService;
import com.paula.checkmc.util.JDBCUtils;

public class ModeloServiceImpl
        implements ModeloService {

    private static final Logger logger =
            LogManager.getLogger(
                    ModeloServiceImpl.class);

    private ModeloDAO modeloDAO =
            new ModeloDAO();

    @Override
    public List<Modelo> findByMarca(Long marcaId)
            throws Exception {

        if (marcaId == null || marcaId <= 0) {
            return new ArrayList<>();
        }

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();

            c.setAutoCommit(false);

            List<Modelo> modelos =
                    modeloDAO.findByMarca(c, marcaId);

            commit = true;

            return modelos;

        } catch (Exception e) {

            logger.error(
                    "Error buscando modelos marca {}: {}",
                    marcaId,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }
}