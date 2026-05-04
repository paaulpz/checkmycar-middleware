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



    public Results<CitaDTO> findByCriteria(CitaCriteria cr, int from, int pageSize)  {
    	// trace
    	//debug
    	//info
    	//warn
    	//error
    	// fatal
    	logger.info("criteria: {} " , cr);
    	
    	 Connection c = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         
        Results<CitaDTO> results = new Results<CitaDTO>(); 
        
        try {
        	c = DAOUtils.getConnection();
        	
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
            
            sql.append(" ORDER BY  ").append(cr.getOrderBy()).append(cr.isAscDesc() ? " ASC " : " DESC ") 
			   .append(" LIMIT ? OFFSET ? ");
            
            if(logger.isInfoEnabled()) {
				logger.info("Criteria SQL: {}: {}: " , cr,  sql);
			}
            
            ps = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
            									ResultSet.CONCUR_READ_ONLY);          
           
            int i = 1; 
            
            for (Object param : parametros) {
                ps.setObject(i++, param);
            }
            
            ps.setInt(i++, pageSize);
            ps.setInt(i++, from - 1); 
            
            rs = ps.executeQuery();
            
            List<Cita> resultsPage = new ArrayList<Cita>();
           
            if (from >= 1) {
         	   int count = 0;
 				rs.absolute(from);
 				do { 
 					resultsPage .add(loadNext(rs));
 					++count;
 				} while(count < pageSize &&  rs.next());
             }
            int totalResults = SQLUtils.getTotalRows(rs);
            
 			results.setPage(null);
 			results.setTotal(totalResults);
 			
            return results;
            
        } catch (Exception e) {
			logger.error(e.getMessage() + ": " + cr , e);
		} finally {
			DAOUtils.close(rs, ps, c);
		}
          return results; 
        }

        
    public Long create(Cita cita) {

        logger.debug("Creando cita: {}", cita);

        try (Connection conn = DAOUtils.getConnection()) {

            String sql = "INSERT INTO appointment(description,date,client_id,car_id,appointment_status_id) VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            DAOUtils.setParameters(ps,
                    cita.getDescripcion(),
                    cita.getFecha(),
                    cita.getClienteId(),
                    cita.getCocheId(),
                    cita.getEstadoCitaId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                logger.info("Cita creada con id: {}", id);
                return id;
            }

        } catch (Exception e) {
            logger.error("Error creando cita: {}", cita, e);
        }

        return null;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando cita id: {}", id);

        try (Connection c = DAOUtils.getConnection()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM appointment WHERE id=?");

            DAOUtils.setParameters(ps, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.error("Error eliminando cita id: {}", id, e);
        }

        return false;
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