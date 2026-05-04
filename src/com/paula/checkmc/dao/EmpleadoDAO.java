package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;
import com.paula.checkmc.util.SQLUtils;

public class EmpleadoDAO {

    private static final Logger logger = LogManager.getLogger(EmpleadoDAO.class);

    private static String BASE_SELECT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e.id, e.name, e.first_surname, e.second_surname, ");
        sb.append("e.dni_nie, e.email, e.phone, ");
        sb.append("e.rol_id, e.gender_id, e.locality_id, e.address, e.password "); 
        sb.append("FROM employee e ");
        BASE_SELECT = sb.toString();
    }

    public Empleado findById(Long id) {
        logger.debug("Buscando empleado id: {}", id);
        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE e.id = ?")) {
            DAOUtils.setParameters(ps, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? loadNext(rs) : null;
        } catch (Exception e) {
            logger.error("Error findById: {}", id, e);
        }
        return null;
    }

    public Empleado findByEmail(String email) {
        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE e.email = ?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? loadNext(rs) : null;
        } catch (Exception e) {
            logger.error("Error findByEmail: {}", email, e);
        }
        return null;
    }

    public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria cr, int from, int pageSize) {

        logger.info("criteria: {}", cr);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<EmpleadoDTO> results = new Results<>();

        try {
            c = DAOUtils.getConnection();

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
                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY ").append(cr.getOrderBy())
               .append(cr.isAscDesc() ? " ASC " : " DESC ")
               .append(" LIMIT ? OFFSET ? ");

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

            List<EmpleadoDTO> resultsPage = new ArrayList<>();
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
                resultsPage.add(dto);
            }

            int totalResults = SQLUtils.getTotalRows(rs);

            results.setPage(resultsPage); // ✅ antes era null
            results.setTotal(totalResults);

            return results;

        } catch (Exception e) {
            logger.error(e.getMessage() + ":" + cr, e);
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return results;
    }

    public Empleado create(Empleado e) {

        String sql = "INSERT INTO employee (name, first_surname, second_surname, dni_nie, " +
                     " email, phone, rol_id, gender_id, locality_id, password, address) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

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

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getLong(1));
                return e;
            }

        } catch (Exception ex) {
            logger.error("Error creando empleado", ex);
        }

        return null;
    }

    public boolean update(Empleado e) {

        String sql = "UPDATE employee SET name=?, first_surname=?, second_surname=?, dni_nie=?, " +
                     " email=?, phone=?, rol_id=?, gender_id=?, locality_id=?, address=? WHERE id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

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
        }

        return false;
    }

    public boolean delete(Long id) {
        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM employee WHERE id=?")) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error("Error delete empleado: {}", id, e);
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