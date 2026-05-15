package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.EmpleadoDAO;
import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.EncryptionService;
import com.paula.checkmc.service.MailService;
import com.paula.checkmc.util.JDBCUtils;

public class EmpleadoServiceImpl implements EmpleadoService {

	private static final Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);

	private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

	private EncryptionService encryptionService = new EncryptionServiceImpl();

	private MailService mailService = new MailServiceApacheImpl();

	@Override
	public Empleado findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Empleado empleado = empleadoDAO.findById(c, id);

			commit = true;

			return empleado;

		} catch (Exception e) {

			logger.error("Error buscando empleado {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Results<EmpleadoDTO> results = empleadoDAO.findByCriteria(c, criteria, from, pageSize);

			commit = true;

			return results;

		} catch (Exception e) {

			logger.error("Buscando {}: {}", criteria, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Long create(Empleado empleado) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Long id = empleadoDAO.create(c, empleado);

			commit = true;

			return id;

		} catch (Exception e) {

			logger.error("Creando {}: {}", empleado, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(Empleado empleado) throws Exception {

		if (empleado.getId() == null || empleado.getId() <= 0) {

			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean updated = empleadoDAO.update(c, empleado);

			commit = true;

			return updated;

		} catch (Exception e) {

			logger.error("Actualizando {}: {}", empleado, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);

		}
	}

	@Override
	public Long register(Empleado empleado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmpleadoDTO login(String dni, String password, Long rolId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}