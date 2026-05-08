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
		sb.append(" SELECT c.id, c.dni_nie, c.name, c.first_surname, c.second_surname, ");
		sb.append(" c.email, c.phone, c.locality_id, c.gender_id, c.password, c.address ");
		sb.append(" FROM client c ");
		BASE_SELECT = sb.toString();
	}

	public Cliente findById(Long id) {

		logger.debug("Buscando cliente por id: {}", id);

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = JDBCUtils.getConnection();

			String sql = BASE_SELECT + " WHERE c.id = ? ";

			ps = c.prepareStatement(sql);
			DAOUtils.setParameters(ps, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				Cliente cliente = loadNext(rs);
				logger.debug("Cliente encontrado: {}", cliente);
				return cliente;
			}

		} catch (Exception e) {
			logger.error("Error buscando cliente por id: {}", id, e);
		} finally {
			DAOUtils.close(rs, ps, c);
		}

		return null;
	}

	public Results<ClienteDTO> findByCriteria(ClienteCriteria cr, int from, int pageSize) {

	    logger.info("criteria: {}", cr);

	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    Results<ClienteDTO> results = new Results<>();

	    try {
	        c = JDBCUtils.getConnection();

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
	            sql.append(" WHERE ").append(String.join(" AND ", condiciones));
	        }

	        sql.append(" ORDER BY ").append(cr.getOrderBy()).append(cr.isAscDesc() ? " ASC " : " DESC ")
	                .append(" LIMIT ? OFFSET ? ");

	        logger.debug("SQL: {}", sql);

	        ps = c.prepareStatement(sql.toString(),
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);

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

	        String countSql = "SELECT COUNT(*) FROM client c";
	        if (!condiciones.isEmpty()) {
	            countSql += " WHERE " + String.join(" AND ", condiciones);
	        }
	        PreparedStatement psCount = c.prepareStatement(countSql);
	        int idx = 1;
	        for (Object param : parametros) {
	            psCount.setObject(idx++, param);
	        }
	        ResultSet rsCount = psCount.executeQuery();
	        if (rsCount.next()) {
	            results.setTotal(rsCount.getInt(1));
	        }

	        return results;

	    } catch (Exception e) {
	        logger.error("Error en findByCriteria: {}", cr, e);
	    } finally {
	        DAOUtils.close(rs, ps, c);
	    }

	    return results;
	}

	public Cliente create(Cliente cliente) {

		logger.debug("Creando cliente: {}", cliente);

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = JDBCUtils.getConnection();

			String sql = " INSERT INTO client "
					+ " (dni_nie, name, first_surname, second_surname, email, locality_id, gender_id, phone, password, address) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, cliente.getDniNie());
			ps.setString(i++, cliente.getNombre());
			ps.setString(i++, cliente.getPrimerApellido());
			ps.setString(i++, cliente.getSegundoApellido());
			ps.setString(i++, cliente.getEmail());
			ps.setLong(i++, cliente.getLocalidadId());
			ps.setLong(i++, cliente.getGeneroId());
			ps.setString(i++, cliente.getTelefono());
			ps.setString(i++, cliente.getPassword());
			ps.setString(i++, cliente.getDireccion());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				cliente.setId(rs.getLong(1));
				logger.info("Cliente creado con id: {}", cliente.getId());
				return cliente;
			}

		} catch (Exception e) {
			logger.error("Error creando cliente: {}", cliente, e);
		} finally {
			DAOUtils.close(rs, ps, c);
		}

		return null;
	}

	public boolean update(Cliente cliente) {

		logger.debug("Actualizando cliente: {}", cliente);

		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = JDBCUtils.getConnection();

			String sql = " UPDATE client SET " + " dni_nie=?, name=?, first_surname=?, second_surname=?, "
					+ " email=?, locality_id=?, gender_id=?, phone=?, password=?, address=? " + " WHERE id=? ";

			ps = c.prepareStatement(sql);

			int i = 1;
			ps.setString(i++, cliente.getDniNie());
			ps.setString(i++, cliente.getNombre());
			ps.setString(i++, cliente.getPrimerApellido());
			ps.setString(i++, cliente.getSegundoApellido());
			ps.setString(i++, cliente.getEmail());
			ps.setLong(i++, cliente.getLocalidadId());
			ps.setLong(i++, cliente.getGeneroId());
			ps.setString(i++, cliente.getTelefono());
			ps.setString(i++, cliente.getPassword());
			ps.setString(i++, cliente.getDireccion());
			ps.setLong(i++, cliente.getId());

			return ps.executeUpdate() == 1;

		} catch (Exception e) {
			logger.error("Error actualizando cliente: {}", cliente, e);
		} finally {
			DAOUtils.close(null, ps, c);
		}

		return false;
	}

	public boolean delete(Long id) {

		logger.warn("Eliminando cliente id: {}", id);

		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = JDBCUtils.getConnection();

			String sql = " DELETE FROM client WHERE id = ? ";

			ps = c.prepareStatement(sql);
			ps.setLong(1, id);

			return ps.executeUpdate() == 1;

		} catch (Exception e) {
			logger.error("Error eliminando cliente id: {}", id, e);
		} finally {
			DAOUtils.close(null, ps, c);
		}

		return false;
	}

	public boolean login(String dni, String password) {

	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        c = JDBCUtils.getConnection();

	        String sql =
	                "SELECT password FROM client WHERE dni_nie = ?";

	        ps = c.prepareStatement(sql);

	        ps.setString(1, dni);

	        rs = ps.executeQuery();

	        if (rs.next()) {

	            String hashedPassword = rs.getString("password");

	            if (hashedPassword.startsWith("$2a$")) {

	                return BCrypt.checkpw(password, hashedPassword);

	            } else {

	                return password.equals(hashedPassword);
	            }
	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    } finally {

	        DAOUtils.close(rs, ps, c);
	    }

	    return false;
	}
	
	public boolean existeCorreo(String email) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			c = JDBCUtils.getConnection();

			String sql = "SELECT * FROM client WHERE email = ?";

			ps = c.prepareStatement(sql);

			ps.setString(1, email);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAOUtils.close(rs, ps, c);
		}

		return false;
	}

	public boolean existeDni(String dni) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			c = JDBCUtils.getConnection();

			String sql = "SELECT * FROM client WHERE dni_nie = ?";

			ps = c.prepareStatement(sql);

			ps.setString(1, dni);

			rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			DAOUtils.close(rs, ps, c);
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