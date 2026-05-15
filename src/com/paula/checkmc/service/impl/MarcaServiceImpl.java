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

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Marca marca = marcaDAO.findById(c, id);

			commit = true;

			return marca;

		} catch (Exception e) {

			logger.error("Error buscando marca {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Marca> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Marca> marcas = marcaDAO.findAll(c);

			commit = true;

			return marcas;

		} catch (Exception e) {

			logger.error("Error listando marcas: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}