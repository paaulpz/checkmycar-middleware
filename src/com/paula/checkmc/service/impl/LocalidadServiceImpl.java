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

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Localidad localidad = localidadDAO.findById(c, id);

			commit = true;

			return localidad;

		} catch (Exception e) {

			logger.error("Error buscando localidad {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Localidad> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Localidad> localidades = localidadDAO.findAll(c);

			commit = true;

			return localidades;

		} catch (Exception e) {

			logger.error("Error listando localidades: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Localidad> findByProvincia(Long provinciaId) throws Exception {

		if (provinciaId == null || provinciaId <= 0) {
			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Localidad> localidades = localidadDAO.findByProvince(c, provinciaId);

			commit = true;

			return localidades;

		} catch (Exception e) {

			logger.error("Error buscando localidades provincia {}: {}", provinciaId, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Localidad> findByNombre(String nombre) throws Exception {

		if (nombre == null || nombre.trim().isEmpty()) {

			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Localidad> localidades = localidadDAO.findByNombre(c, nombre);

			commit = true;

			return localidades;

		} catch (Exception e) {

			logger.error("Error buscando localidad nombre {}: {}", nombre, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}