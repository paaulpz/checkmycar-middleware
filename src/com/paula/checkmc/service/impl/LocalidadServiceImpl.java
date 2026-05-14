package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.LocalidadDAO;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.util.JDBCUtils;

public class LocalidadServiceImpl implements LocalidadService {

    private static final Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);

    private LocalidadDAO localidadDAO = new LocalidadDAO();

    @Override
    public Localidad findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return localidadDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando localidad {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<Localidad> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return localidadDAO.findAll(c);

        } catch (Exception e) {

            logger.error("Error listando localidades: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<Localidad> findByProvincia(Long provinciaId) throws Exception {

        if (provinciaId == null || provinciaId <= 0) {
            return new ArrayList<>();
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return localidadDAO.findByProvince(c, provinciaId);

        } catch (Exception e) {

            logger.error("Error buscando localidades provincia {}: {}", provinciaId, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<Localidad> findByNombre(String nombre) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            return new ArrayList<>();
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return localidadDAO.findByNombre(c, nombre);

        } catch (Exception e) {

            logger.error("Error buscando localidad nombre {}: {}", nombre, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}