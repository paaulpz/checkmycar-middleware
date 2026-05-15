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

    
    public CocheDAO() {
    	
    }
    
    public Coche findById(Connection c, Long id) throws Exception {

        logger.debug("Buscando coche por id: {}", id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = c.prepareStatement(BASE_SELECT + " WHERE c.id = ? ");

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                Coche coche = loadNext(rs);

                logger.debug("Coche encontrado: {}", coche);

                return coche;
            }

            return null;

        } catch (Exception e) {

            logger.error("Error buscando coche id: {}", id, e);

            throw e;

        } finally {

            JDBCUtils.close(rs, ps);
        }
    }

    public Results<CocheDTO> findByCriteria(Connection c, CocheCriteria cr, int from, int pageSize) 
    	throws Exception {

        logger.info("criteria: {}", cr);
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<CocheDTO> results = new Results<>();

        try {
           
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
            throw e;
        } finally {
        	JDBCUtils.close(rs, ps);
        }

    }


    public Long create(Connection c, Coche coche) throws Exception {

        logger.debug("Creando coche: {}", coche);

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO car ");
        sql.append("(diagnostico, car_registration, chassi_number, year, potenciacv, km, ");
        sql.append("client_id, type_fuel_id, transmission_type_id, model_id, type_engine_id) ");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        try {

            PreparedStatement ps = c.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++, coche.getDiagnostico());
            ps.setString(i++, coche.getMatricula());
            ps.setString(i++, coche.getNumeroBastidor());

            if (coche.getAno() != null) {
                ps.setInt(i++, coche.getAno());
            } else {
                ps.setNull(i++, Types.INTEGER);
            }

            ps.setDouble(i++, coche.getPotenciaCv());
            ps.setDouble(i++, coche.getKilometros());
            ps.setLong(i++, coche.getClienteId());
            ps.setLong(i++, coche.getTipoCombustibleId());
            ps.setLong(i++, coche.getTipoTransmisionId());
            ps.setLong(i++, coche.getModeloId());
            ps.setLong(i++, coche.getTipoMotorId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                logger.info("Coche creado con id: {}", id);
                return id;
            } else {
            	return null;
            }
        } catch (Exception e) {
            logger.error("Error creando coche: {}", coche, e);
            throw e;
        }       
    }

    public boolean update(Connection c, Coche coche) throws Exception {

        logger.debug("Actualizando coche: {}", coche);

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE car SET diagnostico=?, car_registration=?, ");
        sql.append("chassi_number=?, year=?, potenciacv=?, km=?, ");
        sql.append("client_id=?, type_fuel_id=?, transmission_type_id=?, ");
        sql.append("model_id=?, type_engine_id=? WHERE id=?");

        try (
        	
            PreparedStatement ps = c.prepareStatement(sql.toString())) {

        	int i = 1;
            ps.setString(i++, coche.getDiagnostico());
            ps.setString(i++, coche.getMatricula());
            
            ps.setString(i++, coche.getNumeroBastidor());
            if (coche.getAno() != null) {
                ps.setInt(i++, coche.getAno());
            } else {
                ps.setNull(i++, Types.INTEGER);
            }
            ps.setDouble(i++, coche.getPotenciaCv());
            ps.setDouble(i++, coche.getKilometros());
            ps.setLong(i++, coche.getClienteId());
            ps.setLong(i++, coche.getTipoCombustibleId());
            ps.setLong(i++, coche.getTipoTransmisionId());
            ps.setLong(i++, coche.getModeloId());
            ps.setLong(i++, coche.getTipoMotorId());
            
            ps.setLong(i++, coche.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error actualizando coche: {}", coche, e);
            throw e;
        }
    }

    public boolean delete(Connection c, Long id) throws Exception {
        logger.warn("Eliminando coche id: {}", id);
        try (        	
            PreparedStatement ps = c.prepareStatement("DELETE FROM car WHERE id=?")) {
            ps.setLong(1, id);
            boolean eliminado = ps.executeUpdate() > 0;
            logger.warn("Coche {} {} {}", id, eliminado?"NO":"",  " eliminado.");
            return eliminado;
        } catch (Exception e) {
            logger.error("Error eliminando coche id: {}", id, e);
            throw e;
        }

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