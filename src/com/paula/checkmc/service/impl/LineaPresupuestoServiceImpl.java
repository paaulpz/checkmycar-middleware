package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.LineaPresupuestoDAO;
import com.paula.checkmc.model.LineaPresupuesto;
import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmc.service.LineaPresupuestoService;
import com.paula.checkmc.util.JDBCUtils;

public class LineaPresupuestoServiceImpl implements LineaPresupuestoService {

	private static final Logger logger = LogManager.getLogger(LineaPresupuestoServiceImpl.class);

	private LineaPresupuestoDAO lineaPresupuestoDAO = new LineaPresupuestoDAO();

	@Override
	public List<LineaPresupuestoDTO> findByPresupuesto(Long presupuestoId) throws Exception {

		if (presupuestoId == null || presupuestoId <= 0) {

			return new ArrayList<>();
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			List<LineaPresupuesto> lineas = lineaPresupuestoDAO.findByPresupuesto(c, presupuestoId);

			List<LineaPresupuestoDTO> res = new ArrayList<>();

			for (LineaPresupuesto l : lineas) {

				LineaPresupuestoDTO dto = new LineaPresupuestoDTO();

				dto.setId(l.getId());

				dto.setUnidades(l.getUnidades());

				dto.setPrecioUnitario(l.getPrecioUnitario());

				dto.setPrecioFinal(l.getPrecioFinal());

				dto.setPiezaId(l.getPiezaId());

				dto.setPresupuestoId(l.getPresupuestoId());

				res.add(dto);
			}

			commit = true;

			return res;

		} catch (Exception e) {

			logger.error("Error buscando lineas presupuesto {}: {}", presupuestoId, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Long create(LineaPresupuestoDTO dto) throws Exception {

		if (!validar(dto)) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			dto.setPrecioFinal(dto.getUnidades() * dto.getPrecioUnitario());

			LineaPresupuesto l = new LineaPresupuesto();

			l.setUnidades(dto.getUnidades());

			l.setPrecioUnitario(dto.getPrecioUnitario());

			l.setPrecioFinal(dto.getPrecioFinal());

			l.setPiezaId(dto.getPiezaId());

			l.setPresupuestoId(dto.getPresupuestoId());

			Long id = lineaPresupuestoDAO.create(c, l);

			commit = true;

			return id;

		} catch (Exception e) {

			logger.error("Error creando linea presupuesto {}: {}", dto, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {

		if (id == null || id <= 0) {
			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean deleted = lineaPresupuestoDAO.delete(c, id);

			commit = true;

			return deleted;

		} catch (Exception e) {

			logger.error("Error eliminando linea presupuesto {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	private boolean validar(LineaPresupuestoDTO l) {

		if (l == null) {
			return false;
		}

		if (l.getUnidades() == null || l.getUnidades() <= 0) {

			return false;
		}

		if (l.getPrecioUnitario() == null || l.getPrecioUnitario() < 0) {

			return false;
		}

		if (l.getPiezaId() == null) {
			return false;
		}

		if (l.getPresupuestoId() == null) {
			return false;
		}

		return true;
	}
}