package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class ClienteDAO {

	private static Logger logger = LogManager.getLogger(ClienteDAO.class);

	private static final String BASE_SELECT;

	static {

		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT c.id, c.dni_nie, c.name, ");
		sb.append(" c.first_surname, c.second_surname, ");
		sb.append(" c.email, c.phone, c.locality_id, ");
		sb.append(" c.gender_id, c.password, c.address ");
		sb.append(" FROM client c ");

		BASE_SELECT = sb.toString();
	}

	public ClienteDAO() {

	}

	public Cliente findById(Connection c, Long id) {

		logger.debug("Buscando cliente por id: {}", id);

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = c.prepareStatement(BASE_SELECT + " WHERE c.id = ? ");

			DAOUtils.setParameters(ps, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				Cliente cliente = loadNext(rs);

				logger.debug("Cliente encontrado: {}", cliente);

				return cliente;
			}

		} catch (Exception e) {

			logger.error("Error buscando cliente id: {}", id, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return null;
	}

	public Results<ClienteDTO> findByCriteria(Connection c, ClienteCriteria cr, int from, int pageSize) {

		logger.info("criteria: {}", cr);

		PreparedStatement ps = null;

		PreparedStatement psCount = null;

		ResultSet rs = null;

		ResultSet rsCount = null;

		Results<ClienteDTO> results = new Results<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			List<String> condiciones = new ArrayList<>();

			List<Object> parametros = new ArrayList<>();

			if (cr.getDniNie() != null && !cr.getDniNie().isEmpty()) {

				condiciones.add(" c.dni_nie = ? ");

				parametros.add(cr.getDniNie());
			}

			if (cr.getEmail() != null && !cr.getEmail().isEmpty()) {

				condiciones.add(" c.email = ? ");

				parametros.add(cr.getEmail());
			}

			if (cr.getLocalidadId() != null) {

				condiciones.add(" c.locality_id = ? ");

				parametros.add(cr.getLocalidadId());
			}

			if (cr.getGeneroId() != null) {

				condiciones.add(" c.gender_id = ? ");

				parametros.add(cr.getGeneroId());
			}

			if (!condiciones.isEmpty()) {

				sql.append(" WHERE ");

				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ");

			sql.append(cr.getOrderBy());

			sql.append(cr.isAscDesc() ? " ASC " : " DESC ");

			sql.append(" LIMIT ? OFFSET ? ");

			logger.debug("SQL: {}", sql);

			ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			for (Object param : parametros) {

				ps.setObject(i++, param);
			}

			ps.setInt(i++, pageSize);

			ps.setInt(i++, from - 1);

			rs = ps.executeQuery();

			List<ClienteDTO> page = new ArrayList<>();

			while (rs.next()) {

				ClienteDTO dto = new ClienteDTO();

				dto.setId(rs.getLong("id"));

				dto.setDniNie(rs.getString("dni_nie"));

				dto.setNombre(rs.getString("name"));

				dto.setPrimerApellido(rs.getString("first_surname"));

				dto.setSegundoApellido(rs.getString("second_surname"));

				dto.setEmail(rs.getString("email"));

				dto.setTelefono(rs.getString("phone"));

				dto.setPassword(rs.getString("password"));

				dto.setLocalidadId(rs.getLong("locality_id"));

				dto.setGeneroId(rs.getLong("gender_id"));

				dto.setDireccion(rs.getString("address"));

				page.add(dto);
			}

			results.setPage(page);

			StringBuilder countSql = new StringBuilder();

			countSql.append("SELECT COUNT(*) ");

			countSql.append("FROM client c ");

			if (!condiciones.isEmpty()) {

				countSql.append(" WHERE ");

				countSql.append(String.join(" AND ", condiciones));
			}

			psCount = c.prepareStatement(countSql.toString());

			int j = 1;

			for (Object param : parametros) {

				psCount.setObject(j++, param);
			}

			rsCount = psCount.executeQuery();

			if (rsCount.next()) {

				results.setTotal(rsCount.getInt(1));
			}

		} catch (Exception e) {

			logger.error("Error en findByCriteria: {}", cr, e);

		} finally {

			JDBCUtils.close(rsCount, psCount);

			JDBCUtils.close(rs, ps);
		}

		return results;
	}

	public Long create(Connection c, Cliente cliente) throws Exception {

		logger.debug("Creando cliente: {}", cliente);

		PreparedStatement ps = null;

		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO client ");

		sql.append("(dni_nie, name, first_surname, second_surname, ");

		sql.append("email, locality_id, gender_id, phone, password, address) ");

		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");

		try {

			ps = c.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			int i = 1;

			ps.setString(i++, cliente.getDniNie());

			ps.setString(i++, cliente.getNombre());

			ps.setString(i++, cliente.getPrimerApellido());

			ps.setString(i++, cliente.getSegundoApellido());

			ps.setString(i++, cliente.getEmail());

			ps.setObject(i++, cliente.getLocalidadId());

			ps.setObject(i++, cliente.getGeneroId());

			ps.setString(i++, cliente.getTelefono());

			ps.setString(i++, cliente.getPassword());

			ps.setString(i++, cliente.getDireccion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {

				Long id = rs.getLong(1);

				logger.info("Cliente creado con id: {}", id);

				return id;
			}

			return null;

		} catch (Exception e) {

			logger.error("Error creando cliente: {}", cliente, e);

			throw e;

		} finally {

			JDBCUtils.close(rs, ps);
		}
	}

	public boolean update(Connection c, Cliente cliente) throws Exception {

		logger.debug("Actualizando cliente: {}", cliente);

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE client SET ");

			sql.append("dni_nie=?, name=?, first_surname=?, ");

			sql.append("second_surname=?, email=?, locality_id=?, ");

			sql.append("gender_id=?, phone=?, password=?, address=? ");

			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;

			ps.setString(i++, cliente.getDniNie());

			ps.setString(i++, cliente.getNombre());

			ps.setString(i++, cliente.getPrimerApellido());

			ps.setString(i++, cliente.getSegundoApellido());

			ps.setString(i++, cliente.getEmail());

			ps.setObject(i++, cliente.getLocalidadId());

			ps.setObject(i++, cliente.getGeneroId());

			ps.setString(i++, cliente.getTelefono());

			ps.setString(i++, cliente.getPassword());

			ps.setString(i++, cliente.getDireccion());

			ps.setLong(i++, cliente.getId());

			return ps.executeUpdate() == 1;

		} catch (Exception e) {

			logger.error("Error actualizando cliente: {}", cliente, e);

			throw e;

		} finally {

			JDBCUtils.close(null, ps);
		}
	}

	public boolean delete(Connection c, Long id) throws Exception {

		logger.warn("Eliminando cliente id: {}", id);

		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("DELETE FROM client ");

			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			ps.setLong(1, id);

			return ps.executeUpdate() == 1;

		} catch (Exception e) {

			logger.error("Error eliminando cliente: {}", id, e);

			throw e;

		} finally {

			JDBCUtils.close(null, ps);
		}
	}

	public boolean login(Connection c, String dni, String password) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT password ");

			sql.append("FROM client ");

			sql.append("WHERE dni_nie=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, dni);

			rs = ps.executeQuery();

			if (rs.next()) {

				String hashedPassword = rs.getString("password");

				if (hashedPassword != null && hashedPassword.startsWith("$2a$")) {

					return BCrypt.checkpw(password, hashedPassword);
				}

				return password.equals(hashedPassword);
			}

		} catch (Exception e) {

			logger.error("Error login cliente: {}", dni, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return false;
	}

	public boolean existeCorreo(Connection c, String email) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT id ");

			sql.append("FROM client ");

			sql.append("WHERE email=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, email);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			logger.error("Error comprobando correo: {}", email, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return false;
	}

	public boolean existeDni(Connection c, String dni) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT id ");

			sql.append("FROM client ");

			sql.append("WHERE dni_nie=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, dni);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			logger.error("Error comprobando dni: {}", dni, e);

		} finally {

			JDBCUtils.close(rs, ps);
		}

		return false;
	}

	private Cliente loadNext(ResultSet rs) throws Exception {

		int i = 1;

		Cliente c = new Cliente();

		c.setId(rs.getLong(i++));

		c.setDniNie(rs.getString(i++));

		c.setNombre(rs.getString(i++));

		c.setPrimerApellido(rs.getString(i++));

		c.setSegundoApellido(rs.getString(i++));

		c.setEmail(rs.getString(i++));

		c.setTelefono(rs.getString(i++));

		c.setLocalidadId(rs.getLong(i++));

		c.setGeneroId(rs.getLong(i++));

		c.setPassword(rs.getString(i++));

		c.setDireccion(rs.getString(i++));

		return c;
	}
}