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

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			EstadoCita estado = estadoCitaDAO.findById(c, id);

			commit = true;

			return estado;

		} catch (Exception e) {

			logger.error("Error buscando estado cita {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<EstadoCita> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<EstadoCita> estados = estadoCitaDAO.findAll(c);

			commit = true;

			return estados;

		} catch (Exception e) {

			logger.error("Error listando estados cita: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}