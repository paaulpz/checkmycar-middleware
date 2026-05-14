package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.MarcaDAO;
import com.paula.checkmc.model.Marca;
import com.paula.checkmc.service.MarcaService;
import com.paula.checkmc.util.JDBCUtils;

public class MarcaServiceImpl implements MarcaService {

    private static final Logger logger = LogManager.getLogger(MarcaServiceImpl.class);

    private MarcaDAO marcaDAO = new MarcaDAO();

    @Override
    public Marca findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return marcaDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando marca {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<Marca> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return marcaDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando marcas: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}