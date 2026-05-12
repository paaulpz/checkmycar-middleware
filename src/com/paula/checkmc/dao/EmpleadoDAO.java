package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class EmpleadoDAO {

	private static final Logger logger = LogManager.getLogger(EmpleadoDAO.class);

	private static final String BASE_SELECT;

	static {

		StringBuilder sb = new StringBuilder();

		sb.append("SELECT e.id, e.name, e.first_surname, ");
		sb.append("e.second_surname, e.dni_nie, e.email, ");
		sb.append("e.phone, e.rol_id, e.gender_id, ");
		sb.append("e.locality_id, e.address, e.password ");
		sb.append("FROM employee e ");

		BASE_SELECT = sb.toString();
	}

	public Empleado findById(Connection c, Long id) {

		logger.debug("Buscando empleado id: {}", id);

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder(BASE_SELECT);

			sql.append("WHERE e.id = ?");

			ps = c.prepareStatement(sql.toString());

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				return loadNext(rs);
			}

		} catch (Exception e) {

			logger.error("Error findById: {}", id, e);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return null;
	}

	public Empleado findByEmail(Connection c, String email) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder(BASE_SELECT);

			sql.append("WHERE e.email = ?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {

				return loadNext(rs);
			}

		} catch (Exception e) {

			logger.error("Error findByEmail: {}", email, e);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return null;
	}

	public Results<EmpleadoDTO> findByCriteria(Connection c, EmpleadoCriteria cr, int from, int pageSize) {

		logger.info("criteria: {}", cr);

		PreparedStatement ps = null;
		PreparedStatement psCount = null;

		ResultSet rs = null;
		ResultSet rsCount = null;

		Results<EmpleadoDTO> results = new Results<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_SELECT);

			List<String> condiciones = new ArrayList<>();
			List<Object> parametros = new ArrayList<>();

			if (cr.getNombre() != null && !cr.getNombre().trim().isEmpty()) {

				condiciones.add("e.name = ?");
				parametros.add(cr.getNombre());
			}

			if (cr.getPrimerApellido() != null && !cr.getPrimerApellido().trim().isEmpty()) {

				condiciones.add("e.first_surname = ?");
				parametros.add(cr.getPrimerApellido());
			}

			if (cr.getSegundoApellido() != null && !cr.getSegundoApellido().trim().isEmpty()) {

				condiciones.add("e.second_surname = ?");
				parametros.add(cr.getSegundoApellido());
			}

			if (cr.getDniNie() != null && !cr.getDniNie().trim().isEmpty()) {

				condiciones.add("e.dni_nie = ?");
				parametros.add(cr.getDniNie());
			}

			if (cr.getEmail() != null && !cr.getEmail().trim().isEmpty()) {

				condiciones.add("e.email = ?");
				parametros.add(cr.getEmail());
			}

			if (cr.getRolId() != null) {

				condiciones.add("e.rol_id = ?");
				parametros.add(cr.getRolId());
			}

			if (cr.getGeneroId() != null) {

				condiciones.add("e.gender_id = ?");
				parametros.add(cr.getGeneroId());
			}

			if (cr.getLocalidadId() != null) {

				condiciones.add("e.locality_id = ?");
				parametros.add(cr.getLocalidadId());
			}

			if (!condiciones.isEmpty()) {

				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}

			sql.append(" ORDER BY ");
			sql.append(cr.getOrderBy());
			sql.append(cr.isAscDesc() ? " ASC " : " DESC ");
			sql.append(" LIMIT ? OFFSET ? ");

			ps = c.prepareStatement(
					sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			for (Object param : parametros) {

				ps.setObject(i++, param);
			}

			ps.setInt(i++, pageSize);
			ps.setInt(i++, from - 1);

			rs = ps.executeQuery();

			List<EmpleadoDTO> page = new ArrayList<>();

			while (rs.next()) {

				EmpleadoDTO dto = new EmpleadoDTO();

				dto.setId(rs.getLong("id"));
				dto.setNombre(rs.getString("name"));
				dto.setPrimerApellido(rs.getString("first_surname"));
				dto.setSegundoApellido(rs.getString("second_surname"));
				dto.setDniNie(rs.getString("dni_nie"));
				dto.setEmail(rs.getString("email"));
				dto.setTelefono(rs.getString("phone"));
				dto.setPassword(rs.getString("password"));
				dto.setRolId(rs.getLong("rol_id"));
				dto.setGeneroId(rs.getLong("gender_id"));
				dto.setLocalidadId(rs.getLong("locality_id"));
				dto.setDireccion(rs.getString("address"));

				page.add(dto);
			}

			results.setPage(page);

			StringBuilder countSql = new StringBuilder();

			countSql.append("SELECT COUNT(*) ");
			countSql.append("FROM employee e ");

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

			logger.error("Error findByCriteria: {}", cr, e);

		} finally {

			DAOUtils.close(rsCount, psCount, null);
			DAOUtils.close(rs, ps, c);
		}

		return results;
	}

	public Empleado create(Connection c, Empleado e) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO employee ");
			sql.append("(name, first_surname, second_surname, dni_nie, ");
			sql.append("email, phone, rol_id, gender_id, locality_id, ");
			sql.append("password, address) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			ps = c.prepareStatement(
					sql.toString(),
					PreparedStatement.RETURN_GENERATED_KEYS);

			int i = 1;

			ps.setString(i++, e.getNombre());
			ps.setString(i++, e.getPrimerApellido());
			ps.setString(i++, e.getSegundoApellido());
			ps.setString(i++, e.getDniNie());
			ps.setString(i++, e.getEmail());
			ps.setString(i++, e.getTelefono());
			ps.setLong(i++, e.getRolId());
			ps.setLong(i++, e.getGeneroId());
			ps.setLong(i++, e.getLocalidadId());
			ps.setString(i++, e.getPassword());
			ps.setString(i++, e.getDireccion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {

				e.setId(rs.getLong(1));

				return e;
			}

		} catch (Exception ex) {

			logger.error("Error creando empleado", ex);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return null;
	}

	public boolean update(Connection c, Empleado e) {

		PreparedStatement ps = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE employee SET ");
			sql.append("name=?, first_surname=?, second_surname=?, ");
			sql.append("dni_nie=?, email=?, phone=?, rol_id=?, ");
			sql.append("gender_id=?, locality_id=?, address=? ");
			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			int i = 1;

			ps.setString(i++, e.getNombre());
			ps.setString(i++, e.getPrimerApellido());
			ps.setString(i++, e.getSegundoApellido());
			ps.setString(i++, e.getDniNie());
			ps.setString(i++, e.getEmail());
			ps.setString(i++, e.getTelefono());
			ps.setLong(i++, e.getRolId());
			ps.setLong(i++, e.getGeneroId());
			ps.setLong(i++, e.getLocalidadId());
			ps.setString(i++, e.getDireccion());
			ps.setLong(i++, e.getId());

			return ps.executeUpdate() > 0;

		} catch (Exception ex) {

			logger.error("Error update empleado", ex);

		} finally {

			DAOUtils.close(null, ps, c);
		}

		return false;
	}

	public boolean delete(Connection c, Long id) {

		PreparedStatement ps = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("DELETE FROM employee ");
			sql.append("WHERE id=?");

			ps = c.prepareStatement(sql.toString());

			ps.setLong(1, id);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			logger.error("Error delete empleado: {}", id, e);

		} finally {

			DAOUtils.close(null, ps, c);
		}

		return false;
	}

	public boolean login(Connection c, String dni, String password, Long rolId) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("SELECT password ");
			sql.append("FROM employee ");
			sql.append("WHERE dni_nie=? ");
			sql.append("AND rol_id=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, dni);
			ps.setLong(2, rolId);

			rs = ps.executeQuery();

			if (rs.next()) {

				String hashedPassword = rs.getString("password");

				return BCrypt.checkpw(password, hashedPassword);
			}

		} catch (Exception e) {

			logger.error("Error login empleado: {}", dni, e);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return false;
	}

	public boolean existeCorreo(Connection c, String email) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("SELECT id ");
			sql.append("FROM employee ");
			sql.append("WHERE email=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, email);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			logger.error("Error comprobando correo: {}", email, e);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return false;
	}

	public boolean existeDni(Connection c, String dni) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			StringBuilder sql = new StringBuilder();

			sql.append("SELECT id ");
			sql.append("FROM employee ");
			sql.append("WHERE dni_nie=?");

			ps = c.prepareStatement(sql.toString());

			ps.setString(1, dni);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			logger.error("Error comprobando dni: {}", dni, e);

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return false;
	}

	private Empleado loadNext(ResultSet rs) throws Exception {

		int i = 1;

		Empleado e = new Empleado();

		e.setId(rs.getLong(i++));
		e.setNombre(rs.getString(i++));
		e.setPrimerApellido(rs.getString(i++));
		e.setSegundoApellido(rs.getString(i++));
		e.setDniNie(rs.getString(i++));
		e.setEmail(rs.getString(i++));
		e.setTelefono(rs.getString(i++));
		e.setRolId(rs.getLong(i++));
		e.setGeneroId(rs.getLong(i++));
		e.setLocalidadId(rs.getLong(i++));
		e.setDireccion(rs.getString(i++));
		e.setPassword(rs.getString(i++));

		return e;
	}
}