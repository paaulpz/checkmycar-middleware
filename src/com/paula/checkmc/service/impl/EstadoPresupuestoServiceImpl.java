package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.EstadoPresupuestoDAO;
import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.service.EstadoPresupuestoService;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPresupuestoServiceImpl implements EstadoPresupuestoService {

	private static final Logger logger = LogManager.getLogger(EstadoPresupuestoServiceImpl.class);

	private EstadoPresupuestoDAO estadoPresupuestoDAO = new EstadoPresupuestoDAO();

	@Override
	public EstadoPresupuesto findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			EstadoPresupuesto estado = estadoPresupuestoDAO.findById(c, id);

			commit = true;

			return estado;

		} catch (Exception e) {

			logger.error("Error buscando estado presupuesto {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<EstadoPresupuesto> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<EstadoPresupuesto> estados = estadoPresupuestoDAO.findAll(c);

			commit = true;

			return estados;

		} catch (Exception e) {

			logger.error("Error listando estados presupuesto: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}