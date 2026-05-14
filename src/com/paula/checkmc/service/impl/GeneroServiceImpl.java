package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.GeneroDAO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.service.GeneroService;
import com.paula.checkmc.util.JDBCUtils;

public class GeneroServiceImpl implements GeneroService {

    private static final Logger logger = LogManager.getLogger(GeneroServiceImpl.class);

    private GeneroDAO generoDAO = new GeneroDAO();

    @Override
    public Genero findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return generoDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando genero {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<Genero> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return generoDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando generos: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}