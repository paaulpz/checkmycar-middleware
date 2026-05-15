package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.PaisDAO;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.util.JDBCUtils;

public class PaisServiceImpl implements PaisService {

	private static final Logger logger = LogManager.getLogger(PaisServiceImpl.class);

	private PaisDAO paisDAO = new PaisDAO();

	@Override
	public Pais findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Pais pais = paisDAO.findById(c, id);

			commit = true;

			return pais;

		} catch (Exception e) {

			logger.error("Error buscando pais {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Pais> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Pais> paises = paisDAO.findAll(c);

			commit = true;

			return paises;

		} catch (Exception e) {

			logger.error("Error listando paises: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}