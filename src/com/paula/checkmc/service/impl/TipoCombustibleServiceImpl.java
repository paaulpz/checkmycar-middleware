package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.TipoCombustibleDAO;
import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.service.TipoCombustibleService;
import com.paula.checkmc.util.JDBCUtils;

public class TipoCombustibleServiceImpl implements TipoCombustibleService {

	private static final Logger logger = LogManager.getLogger(TipoCombustibleServiceImpl.class);

	private TipoCombustibleDAO tipoCombustibleDAO = new TipoCombustibleDAO();

	@Override
	public TipoCombustible findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			TipoCombustible tipoCombustible = tipoCombustibleDAO.findById(c, id);

			commit = true;

			return tipoCombustible;

		} catch (Exception e) {

			logger.error("Error buscando tipo combustible {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<TipoCombustible> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<TipoCombustible> tiposCombustible = tipoCombustibleDAO.findAll(c);

			commit = true;

			return tiposCombustible;

		} catch (Exception e) {

			logger.error("Error listando tipos combustible: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}