package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.ProvinciaDAO;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.ProvinciaService;
import com.paula.checkmc.util.JDBCUtils;

public class ProvinciaServiceImpl implements ProvinciaService {

	private static final Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class);

	private ProvinciaDAO provinciaDAO = new ProvinciaDAO();

	@Override
	public Provincia findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Provincia provincia = provinciaDAO.findById(c, id);

			commit = true;

			return provincia;

		} catch (Exception e) {

			logger.error("Error buscando provincia {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Provincia> findByPais(Long paisId) throws Exception {

		if (paisId == null || paisId <= 0) {
			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Provincia> provincias = provinciaDAO.findByPais(c, paisId);

			commit = true;

			return provincias;

		} catch (Exception e) {

			logger.error("Error buscando provincias pais {}: {}", paisId, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Provincia> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Provincia> provincias = provinciaDAO.findAll(c);

			commit = true;

			return provincias;

		} catch (Exception e) {

			logger.error("Error listando provincias: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}