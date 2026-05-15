package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.PresupuestoEmpleadoDAO;
import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.service.PresupuestoEmpleadoService;
import com.paula.checkmc.util.JDBCUtils;

public class PresupuestoEmpleadoServiceImpl implements PresupuestoEmpleadoService {

	private static final Logger logger = LogManager.getLogger(PresupuestoEmpleadoServiceImpl.class);

	private PresupuestoEmpleadoDAO presupuestoEmpleadoDAO = new PresupuestoEmpleadoDAO();

	@Override
	public boolean create(PresupuestoEmpleado pe) throws Exception {

		if (pe == null) {
			return false;
		}

		if (pe.getEmpleadoId() == null || pe.getEmpleadoId() <= 0) {

			return false;
		}

		if (pe.getPresupuestoId() == null || pe.getPresupuestoId() <= 0) {

			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean created = presupuestoEmpleadoDAO.create(c, pe);

			commit = true;

			return created;

		} catch (Exception e) {

			logger.error("Error creando presupuesto empleado {}: {}", pe, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean delete(Long empleadoId, Long presupuestoId) throws Exception {

		if (empleadoId == null || empleadoId <= 0) {
			return false;
		}

		if (presupuestoId == null || presupuestoId <= 0) {
			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean deleted = presupuestoEmpleadoDAO.delete(c, empleadoId, presupuestoId);

			commit = true;

			return deleted;

		} catch (Exception e) {

			logger.error("Error eliminando presupuesto empleado {} {}: {}", empleadoId, presupuestoId, e.getMessage(),
					e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Empleado> findPresupuestosByEmpleado(Long empleadoId) throws Exception {

		if (empleadoId == null || empleadoId <= 0) {
			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Empleado> empleados = presupuestoEmpleadoDAO.findPresupuestosByEmpleado(c, empleadoId);

			commit = true;

			return empleados;

		} catch (Exception e) {

			logger.error("Error buscando presupuestos empleado {}: {}", empleadoId, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public List<Long> findEmpleadosByPresupuesto(Long presupuestoId) throws Exception {

		if (presupuestoId == null || presupuestoId <= 0) {
			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<Long> empleados = presupuestoEmpleadoDAO.findEmpleadosByPresupuesto(c, presupuestoId);

			commit = true;

			return empleados;

		} catch (Exception e) {

			logger.error("Error buscando empleados presupuesto {}: {}", presupuestoId, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}