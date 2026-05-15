package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.CocheDAO;
import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CocheService;
import com.paula.checkmc.util.JDBCUtils;

public class CocheServiceImpl implements CocheService {

	private Logger logger = LogManager.getLogger(CocheServiceImpl.class.getName());

	private CocheDAO cocheDAO = new CocheDAO();

	@Override
	public Coche findById(Long id) throws Exception {

		if (id == null || id <= 0) {

			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Coche coche = cocheDAO.findById(c, id);

			commit = true;

			return coche;

		} catch (Exception e) {

			logger.error("Error buscando coche {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Results<CocheDTO> findByCriteria(CocheCriteria criteria, int from, int pageSize) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Results<CocheDTO> results = cocheDAO.findByCriteria(c, criteria, from, pageSize);

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
	public Long create(Coche coche) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Long id = cocheDAO.create(c, coche);

			commit = true;

			return id;

		} catch (Exception e) {

			logger.error("Creando {}: {}", coche, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(Coche coche) throws Exception {

		if (coche.getId() == null || coche.getId() <= 0) {

			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean updated = cocheDAO.update(c, coche);

			commit = true;

			return updated;

		} catch (Exception e) {

			logger.error("Actualizando {}: {}", coche, e.getMessage(), e);

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

			boolean deleted = cocheDAO.delete(c, id);

			commit = true;

			return deleted;

		} catch (Exception e) {

			logger.error("Error al eliminar coche {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}