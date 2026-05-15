package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Pieza;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.util.JDBCUtils;

public class PiezaDAO {

	private static final Logger logger = LogManager.getLogger(PiezaDAO.class);

	private static final String BASE_SELECT;

	static {

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT p.id, ");
		sb.append("p.name, ");
		sb.append("p.stock, ");
		sb.append("p.part_status_id, ");
		sb.append("ps.name, ");
		sb.append("p.price, ");
		sb.append("p.reference_number ");
		sb.append("FROM part p ");
		sb.append("LEFT JOIN part_status ps ");
		sb.append("ON ps.id = p.part_status_id ");

		BASE_SELECT = sb.toString();
	}

	public Pieza findById(Connection c, Long id) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			sql.append("WHERE p.id=?");

			ps = c.prepareStatement(sql.toString());

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				return loadNext(rs);
			}

		} catch (Exception e) {

			logger.error("Error buscando pieza: {}", id, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public List<Pieza> findAll(Connection c) {

		List<Pieza> lista = new ArrayList<>();

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			sql.append("ORDER BY p.name");

			ps = c.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {

				lista.add(loadNext(rs));
			}

		} catch (Exception e) {

			logger.error("Error listando piezas", e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return lista;
	}

	public List<Pieza> findByCriteria(Connection c, PiezaCriteria cr) {

		List<Pieza> lista = new ArrayList<>();

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			List<String> condiciones = new ArrayList<>();

			List<Object> parametros = new ArrayList<>();

			if (cr.getNombre() != null && !cr.getNombre().trim().isEmpty()) {

				condiciones.add("UPPER(p.name) LIKE UPPER(?)");

				parametros.add("%" + cr.getNombre() + "%");
			}

			if (cr.getEstadoId() != null) {

				condiciones.add("p.part_status_id=?");

				parametros.add(cr.getEstadoId());
			}

			if (cr.getNumeroReferencia() != null && !cr.getNumeroReferencia().trim().isEmpty()) {

				condiciones.add("UPPER(p.reference_number) LIKE UPPER(?)");

				parametros.add("%" + cr.getNumeroReferencia() + "%");
			}

			if (!condiciones.isEmpty()) {

				sql.append(" WHERE ");

				sql.append(String.join(" AND ", condiciones));
			}

			ps = c.prepareStatement(sql.toString());

			int i = 1;

			for (Object param : parametros) {

				ps.setObject(i++, param);
			}

			rs = ps.executeQuery();

			while (rs.next()) {

				lista.add(loadNext(rs));
			}

		} catch (Exception e) {

			logger.error("Error buscando piezas", e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return lista;
	}

	private Pieza loadNext(ResultSet rs) throws Exception {

		int i = 1;

		Pieza p = new Pieza();

		p.setId(rs.getLong(i++));
		p.setNombre(rs.getString(i++));
		p.setStock(rs.getInt(i++));
		p.setEstadoId(rs.getLong(i++));
		p.setEstadoNombre(rs.getString(i++));
		p.setPrecio(rs.getBigDecimal(i++));
		p.setNumeroReferencia(rs.getString(i++));

		return p;
	}
}