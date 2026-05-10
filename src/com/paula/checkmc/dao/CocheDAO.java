package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class CocheDAO {

    private static final Logger logger = LogManager.getLogger(CocheDAO.class);

    private static final String BASE_SELECT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT c.id, c.car_registration, c.chassi_number, c.year, ");
        sb.append(" c.potenciacv, c.km, c.diagnostico, ");
        sb.append(" c.client_id, c.model_id, c.type_fuel_id, ");
        sb.append(" c.transmission_type_id, c.type_engine_id, ");
        sb.append(" mo.name AS nombre_modelo, ma.name AS nombre_marca, ");
        sb.append(" cl.dni_nie AS dni_cliente, ");
        sb.append(" tf.name AS nombre_combustible, tt.name AS nombre_transmision, te.name AS nombre_motor ");
        sb.append(" FROM car c ");
        sb.append(" LEFT JOIN model mo ON c.model_id = mo.id ");
        sb.append(" LEFT JOIN client cl ON c.client_id = cl.id ");
        sb.append(" LEFT JOIN brand ma ON mo.brand_id = ma.id ");
        sb.append(" LEFT JOIN type_fuel tf ON c.type_fuel_id = tf.id ");
        sb.append(" LEFT JOIN transmission_type tt ON c.transmission_type_id = tt.id ");
        sb.append(" LEFT JOIN type_engine te ON c.type_engine_id = te.id ");
        BASE_SELECT = sb.toString();
    }

    public Coche findById(Long id) {
        logger.debug("Buscando coche por id: {}", id);
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = JDBCUtils.getConnection();
            ps = c.prepareStatement(BASE_SELECT + " WHERE c.id = ? ");
            DAOUtils.setParameters(ps, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Coche coche = loadNext(rs);
                logger.debug("Coche encontrado: {}", coche);
                return coche;
            }
        } catch (Exception e) {
            logger.error("Error buscando coche id: {}", id, e);
        } finally {
            DAOUtils.close(rs, ps, c);
        }
        return null;
    }

    public Results<CocheDTO> findByCriteria(CocheCriteria cr, int from, int pageSize) {

        logger.info("criteria: {}", cr);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<CocheDTO> results = new Results<>();

        try {
            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);
            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getMatricula() != null && !cr.getMatricula().isEmpty()) {
                condiciones.add(" c.car_registration = ? ");
                parametros.add(cr.getMatricula());
            }

            if (cr.getNumeroBastidor() != null && !cr.getNumeroBastidor().isEmpty()) {
                condiciones.add(" c.chassi_number = ? ");
                parametros.add(cr.getNumeroBastidor());
            }

            if (cr.getClienteId() != null) {
                condiciones.add(" c.client_id = ? ");
                parametros.add(cr.getClienteId());
            }

            if (cr.getMarcaId() != 0) {
                condiciones.add(" ma.id = ? ");
                parametros.add(cr.getMarcaId());
            }

            if (cr.getModeloId() != null) {
                condiciones.add(" c.model_id = ? ");
                parametros.add(cr.getModeloId());
            }

            if (cr.getTipoCombustibleId() != null) {
                condiciones.add(" c.type_fuel_id = ? ");
                parametros.add(cr.getTipoCombustibleId());
            }

            if (cr.getTipoMotorId() != null) {
                condiciones.add(" c.type_engine_id = ? ");
                parametros.add(cr.getTipoMotorId());
            }

            if (cr.getTipoTransmisionId() != null) {
                condiciones.add(" c.transmission_type_id = ? ");
                parametros.add(cr.getTipoTransmisionId());
            }

          

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            String orderBy = cr.getOrderBy() != null ? cr.getOrderBy() : "c.id";
            sql.append(" ORDER BY ").append(orderBy)
               .append(cr.isAscDesc() ? " ASC " : " DESC ")
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

            List<CocheDTO> page = new ArrayList<>();

            while (rs.next()) {
                CocheDTO dto = new CocheDTO();
                dto.setId(rs.getLong("id"));
                dto.setMatricula(rs.getString("car_registration"));
                dto.setNumeroBastidor(rs.getString("chassi_number"));
                dto.setAno(rs.getInt("year"));
                dto.setPotenciaCv(rs.getInt("potenciacv"));
                dto.setKilometros(rs.getDouble("km"));
                dto.setDiagnostico(rs.getString("diagnostico"));
                dto.setClienteId(rs.getLong("client_id"));
                dto.setModeloId(rs.getLong("model_id"));
                dto.setTipoCombustibleId(rs.getLong("type_fuel_id"));
                dto.setTipoTransmisionId(rs.getLong("transmission_type_id"));
                dto.setTipoMotorId(rs.getLong("type_engine_id"));
                dto.setNombreModelo(rs.getString("nombre_modelo"));
                dto.setNombreMarca(rs.getString("nombre_marca"));
                dto.setNombreCliente(rs.getString("dni_cliente"));
                dto.setTipoCombustible(rs.getString("nombre_combustible"));
                dto.setTipoTransmision(rs.getString("nombre_transmision"));
                dto.setTipoMotor(rs.getString("nombre_motor"));
                page.add(dto);
            }

            results.setPage(page);

            StringBuilder countSql = new StringBuilder();
            countSql.append(" SELECT COUNT(*) FROM car c ");
            countSql.append(" LEFT JOIN model mo ON c.model_id = mo.id ");
            countSql.append(" LEFT JOIN client cl ON c.client_id = cl.id ");
            countSql.append(" LEFT JOIN brand ma ON mo.brand_id = ma.id ");
            countSql.append(" LEFT JOIN type_fuel tf ON c.type_fuel_id = tf.id ");
            countSql.append(" LEFT JOIN transmission_type tt ON c.transmission_type_id = tt.id ");
            countSql.append(" LEFT JOIN type_engine te ON c.type_engine_id = te.id ");

            if (!condiciones.isEmpty()) {
                countSql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }

            PreparedStatement psCount = c.prepareStatement(countSql.toString());
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

    public Long create(Coche coche) {

        logger.debug("Creando coche: {}", coche);

        String sql =
        	    " INSERT INTO car " +
        	    " (diagnostico, car_registration, chassi_number, year, potenciacv, km, " +
        	    " client_id, type_fuel_id, transmission_type_id, model_id, type_engine_id) " +
        	    " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, coche.getDiagnostico());
            ps.setString(2, coche.getMatricula());
            ps.setString(3, coche.getNumeroBastidor());
            if (coche.getAno() != null) {
                ps.setInt(4, coche.getAno());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setDouble(5, coche.getPotenciaCv());
            ps.setDouble(6, coche.getKilometros());
            ps.setLong(7, coche.getClienteId());
            ps.setLong(8, coche.getTipoCombustibleId());
            ps.setLong(9, coche.getTipoTransmisionId());
            ps.setLong(10, coche.getModeloId());
            ps.setLong(11, coche.getTipoMotorId());
            
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                logger.info("Coche creado con id: {}", id);
                return id;
            }

        } catch (Exception e) {
            logger.error("Error creando coche: {}", coche, e);
        }

        return null;
    }

    public boolean update(Coche coche) {

        logger.debug("Actualizando coche: {}", coche);

        String sql =
            " UPDATE car SET diagnostico=?, car_registration=?, chassi_number=?, year=?, " +
            " potenciacv=?, km=?, client_id=?, type_fuel_id=?, transmission_type_id=?, " +
            " model_id=?, type_engine_id=? WHERE id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, coche.getDiagnostico());
            ps.setString(2, coche.getMatricula());
            ps.setString(3, coche.getNumeroBastidor());
            if (coche.getAno() != null) {
                ps.setInt(4, coche.getAno());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setDouble(5, coche.getPotenciaCv());
            ps.setDouble(6, coche.getKilometros());
            ps.setLong(7, coche.getClienteId());
            ps.setLong(8, coche.getTipoCombustibleId());
            ps.setLong(9, coche.getTipoTransmisionId());
            ps.setLong(10, coche.getModeloId());
            ps.setLong(11, coche.getTipoMotorId());
            
            ps.setLong(12, coche.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error actualizando coche: {}", coche, e);
        }

        return false;
    }

    public boolean delete(Long id) {
        logger.warn("Eliminando coche id: {}", id);
        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM car WHERE id=?")) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error("Error eliminando coche id: {}", id, e);
        }
        return false;
    }

    private Coche loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Coche c = new Coche();
        c.setId(rs.getLong(i++));
        c.setMatricula(rs.getString(i++));
        c.setNumeroBastidor(rs.getString(i++));
        c.setAno(rs.getInt(i++));
        c.setPotenciaCv(rs.getInt(i++));
        c.setKilometros(rs.getInt(i++));
        c.setDiagnostico(rs.getString(i++));
        c.setClienteId(rs.getLong(i++));
        c.setModeloId(rs.getLong(i++));
        c.setTipoCombustibleId(rs.getLong(i++));
        c.setTipoTransmisionId(rs.getLong(i++));
        c.setTipoMotorId(rs.getLong(i++));
        return c;
    }
}