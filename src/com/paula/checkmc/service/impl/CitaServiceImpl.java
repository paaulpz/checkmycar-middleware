package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.CitaDAO;
import com.paula.checkmc.dao.ClienteDAO;
import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CitaService;
import com.paula.checkmc.service.MailService;
import com.paula.checkmc.util.JDBCUtils;

public class CitaServiceImpl implements CitaService {

	private Logger logger = LogManager.getLogger(CitaServiceImpl.class.getName());

	private CitaDAO citaDAO = new CitaDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();

	private MailService mailService = new MailServiceApacheImpl();

	@Override
	public Cita findById(Long id) throws Exception {

		if (id == null || id <= 0) {

			return null;
		}

		Connection c = null;

		try {

			c = JDBCUtils.getConnection();

			return citaDAO.findById(c, id);

		} catch (Exception e) {

			logger.error("Error buscando cita {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, true);
		}
	}

	@Override
	public Results<CitaDTO> findByCriteria(CitaCriteria criteria, int from, int pageSize) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Results<CitaDTO> results = citaDAO.findByCriteria(c, criteria, from, pageSize);

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
	public Long create(Cita cita) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Long id = citaDAO.create(c, cita);

			commit = true;

			return id;

		} catch (Exception e) {

			logger.error("Creando {}: {}", cita, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(Cita cita) throws Exception {

		if (cita == null || cita.getId() == null || cita.getId() <= 0) {

			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean updated = citaDAO.update(c, cita);

			if (updated && cita.getEstadoCitaId() != null && cita.getEstadoCitaId().equals(2L)) {

				Cliente cliente = clienteDAO.findById(c, cita.getClienteId());

				if (cliente != null && cliente.getEmail() != null) {

					mailService.sendEmail(
							cliente.getEmail(), "Cita confirmada", "Hola " + cliente.getNombre()
									+ ", su cita para el día " + cita.getFecha() +  " ha sido confirmada.",
							"Departamento de Gestión y Mantenimiento CheckMyCar");
				}
			}

			commit = true;

			return updated;

		} catch (Exception e) {

			logger.error("Actualizando {}: {}", cita, e.getMessage(), e);

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

			boolean deleted = citaDAO.delete(c, id);

			commit = true;

			return deleted;

		} catch (Exception e) {

			logger.error("Error eliminando cita {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean existeCitaEnFecha(LocalDateTime fecha) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean exists = citaDAO.existeCitaEnFecha(fecha, c);

			commit = true;

			return exists;

		} catch (Exception e) {

			logger.error("Error verificando cita en fecha {}: {}", fecha, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}