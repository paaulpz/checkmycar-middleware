package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.RolDAO;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.RolService;
import com.paula.checkmc.util.JDBCUtils;

public class RolServiceImpl implements RolService {

	private static final Logger logger = LogManager.getLogger(RolServiceImpl.class);

	private RolDAO rolDAO = new RolDAO();

	@Override
	public Rol findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Rol rol = rolDAO.findById(c, id);

			commit = true;

			return rol;

		} catch (Exception e) {

			logger.error("Error buscando rol {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Rol> findAll() throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Rol> roles = rolDAO.findAll(c);

			commit = true;

			return roles;

		} catch (Exception e) {

			logger.error("Error listando roles: {}", e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}