package com.paula.checkmc.service;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Results;

public interface CitaService {
	
	/**
	 * Busca una cita por su ID.
	 * @param criteria
	 * @return
	 * @throws Exception
	 */

  public  Results<CitaDTO> findByCriteria(CitaCriteria criteria , int from, int pageSize) 	throws Exception;
  
  
    
    /**
	 * Crea una nueva cita.
	 * @param cita
	 * @return El ID de la cita creada.
	 * @throws Exception
	 */

  public  Long create(Cita cita) throws Exception;
  
  
    
    /**
     * Elimina una cita existente.
     * @param id
     * @return
     * @throws Exception
     */

  public  boolean delete(Long id) throws Exception;
  
  
    
  /**
   * Actualiza una cita existente.
   * @param cita
   * @return
   * @throws Exception
   */
    
   public  boolean update(Cita cita) 	throws Exception;
   
   
   
   /**
    * Verifica si existe una cita en la fecha dada.
    * @param fecha
    * @return
    * @throws Exception
    */ 
  public   boolean existeCitaEnFecha(java.time.LocalDateTime fecha) throws Exception;


  /**   * Busca una cita por su ID.
   * @param id
   * @return
   * @throws Exception
   */
   public Cita findById(Long id)throws Exception;

  
}