package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.TipoMotorDAO;
import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.service.TipoMotorService;
import com.paula.checkmc.util.JDBCUtils;

public class TipoMotorServiceImpl implements TipoMotorService {

	private static final Logger logger = LogManager.getLogger(TipoMotorServiceImpl.class);

	private TipoMotorDAO tipoMotorDAO = new TipoMotorDAO();

	@Override
	public TipoMotor findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			TipoMotor tipoMotor = tipoMotorDAO.findById(c, id);

			commit = true;

			return tipoMotor;

		} catch (Exception e) {

			logger.error("Error buscando tipo motor {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<TipoMotor> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<TipoMotor> tiposMotor = tipoMotorDAO.findAll(c);

			commit = true;

			return tiposMotor;

		} catch (Exception e) {

			logger.error("Error listando tipos motor: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}