package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;
import com.paula.checkmc.util.SQLUtils;

public class CitaDAO {

	private Logger logger = LogManager.getLogger(CitaDAO.class);

	private static final String BASE_SELECT;

	static {

		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT a.id, a.description, a.date, ");

		sb.append(" a.client_id, a.car_id, a.appointment_status_id ");

		sb.append(" FROM appointment a ");

		BASE_SELECT = sb.toString();
	}

	public Cita findById(Connection c, Long id) throws Exception {

		logger.debug("Buscando cita por id: {}", id);

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			sql.append(" WHERE a.id = ? ");

			ps = c.prepareStatement(sql.toString());

			DAOUtils.setParameters(ps, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				Cita cita = loadNext(rs);

				logger.debug("Cita encontrada: {}", cita);

				return cita;
			}

			return null;

		} catch (Exception e) {

			logger.error("Error buscando cita id: {}", id, e);

			throw e;

		} finally {

			JDBCUtils.close(rs, ps);
		}
	}

	public Results<CitaDTO> findByCriteria(Connection c, CitaCriteria cr, int from, int pageSize) throws Exception {

		logger.info("criteria: {}", cr);

		PreparedStatement ps = null;

		ResultSet rs = null;

		Results<CitaDTO> results = new Results<CitaDTO>();

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			List<String> condiciones = new ArrayList<>();

			List<Object> parametros = new ArrayList<>();

			if (cr.getClienteId() != null) {

				condiciones.add(" a.client_id = ?");

				parametros.add(cr.getClienteId());
			}

			if (cr.getCocheId() != null) {

				condiciones.add(" a.car_id = ?");

				parametros.add(cr.getCocheId());
			}

			if (cr.getEstadoCitaId() != null) {

				condiciones.add(" a.appointment_status_id = ?");

				parametros.add(cr.getEstadoCitaId());
			}

			if (cr.getFechaDesde() != null) {

				condiciones.add(" a.date >= ?");

				parametros.add(cr.getFechaDesde());
			}

			if (cr.getFechaHasta() != null) {

				condiciones.add(" a.date <= ?");

				parametros.add(cr.getFechaHasta());
			}

			if (!condiciones.isEmpty()) {

				sql.append(" WHERE ");

				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ");

			String orderBy = cr.getOrderBy() != null ? cr.getOrderBy() : "a.id";

			sql.append(orderBy);

			sql.append(cr.isAscDesc() ? " ASC " : " DESC ");

			sql.append(" LIMIT ? OFFSET ? ");

			logger.info("SQL: {}", sql);

			ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			for (Object param : parametros) {

				ps.setObject(i++, param);
			}

			ps.setInt(i++, pageSize);

			ps.setInt(i++, from - 1);

			rs = ps.executeQuery();

			List<CitaDTO> lista = new ArrayList<>();

			while (rs.next()) {

				CitaDTO dto = new CitaDTO();

				dto.setId(rs.getLong("id"));

				dto.setDescripcion(rs.getString("description"));

				dto.setFecha(rs.getTimestamp("date").toLocalDateTime());

				dto.setClienteId(rs.getLong("client_id"));

				dto.setCocheId(rs.getLong("car_id"));

				dto.setEstadoCitaId(rs.getLong("appointment_status_id"));

				lista.add(dto);
			}

			int totalResults = SQLUtils.getTotalRows(rs);

			results.setPage(lista);

			results.setTotal(totalResults);

		} catch (Exception e) {

			logger.error("Error buscando citas: {}", cr, e);

			throw e;

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return results;
	}

	public Long create(Connection c, Cita cita) throws Exception {

		logger.debug("Creando cita: {}", cita);

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO appointment ");

			sql.append("(description, date, client_id, car_id, appointment_status_id) ");

			sql.append("VALUES (?,?,?,?,?)");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			DAOUtils.setParameters(ps, cita.getDescripcion(), cita.getFecha(), cita.getClienteId(), cita.getCocheId(),
					cita.getEstadoCitaId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {

				Long id = rs.getLong(1);

				logger.info("Cita creada con id: {}", id);

				return id;
			}

			return null;

		} catch (Exception e) {

			logger.error("Error creando cita: {}", cita, e);

			throw e;

		} finally {

			JDBCUtils.close(rs, ps);
		}
	}

	public boolean delete(Connection c, Long id) throws Exception {

		logger.warn("Eliminando cita id: {}", id);

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("DELETE FROM appointment ");

			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			DAOUtils.setParameters(ps, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			logger.error("Error eliminando cita id: {}", id, e);

			throw e;

		} finally {

			JDBCUtils.close(null, ps);
		}
	}

	public boolean existeCitaEnFecha(java.time.LocalDateTime fecha, Connection c) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT COUNT(*) ");

			sql.append("FROM appointment ");

			sql.append("WHERE date=?");

			ps = c.prepareStatement(sql.toString());

			ps.setObject(1, fecha);

			rs = ps.executeQuery();

			if (rs.next()) {

				return rs.getInt(1) > 0;
			}

		} catch (Exception e) {

			logger.error("Error comprobando cita en fecha: {}", fecha, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return false;
	}

	public boolean update(Connection c, Cita cita) throws Exception {

		logger.debug("Actualizando cita: {}", cita);

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE appointment SET ");

			sql.append("description=?, ");

			sql.append("date=?, ");

			sql.append("client_id=?, ");

			sql.append("car_id=?, ");

			sql.append("appointment_status_id=? ");

			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			DAOUtils.setParameters(ps, cita.getDescripcion(), cita.getFecha(), cita.getClienteId(), cita.getCocheId(),
					cita.getEstadoCitaId(), cita.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			logger.error("Error actualizando cita: {}", cita, e);

			throw e;

		} finally {

			JDBCUtils.close(null, ps);
		}
	}

	private Cita loadNext(ResultSet rs) throws Exception {

		int i = 1;

		Cita c = new Cita();

		c.setId(rs.getLong(i++));

		c.setDescripcion(rs.getString(i++));

		c.setFecha(rs.getTimestamp(i++).toLocalDateTime());

		c.setClienteId(rs.getLong(i++));

		c.setCocheId(rs.getLong(i++));

		c.setEstadoCitaId(rs.getLong(i++));

		return c;
	}
}