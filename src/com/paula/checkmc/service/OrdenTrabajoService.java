package com.paula.checkmc.service;

import com.paula.checkmc.model.OrdenTrabajoCriteria;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.Results;

public interface OrdenTrabajoService {
	
	/**
	 * Busca una orden de trabajo por su id
	 * @param id
	 * @return
	 * @throws Exception
	 */

    OrdenTrabajoDTO findById(Long id) throws Exception;
    
    /**
     * Busca todas las ordenes de trabajo
     * @param criteria
     * @return
     * @throws Exception
     */

  
    Results<OrdenTrabajoDTO> findByCriteria(OrdenTrabajoCriteria criteria, int from , int pageSize);
    
    
    /**
     * Crea una nueva orden de trabajo
     * @param orden
     * @return
     * @throws Exception
     */

    Long create(OrdenTrabajoDTO orden) throws Exception;
    
    /**
     * Actualiza una orden de trabajo existente
     * @param orden
     * @return
     * @throws Exception
     */
    boolean update(OrdenTrabajoDTO orden) throws Exception;
    
    /**
	 * Elimina una orden de trabajo por su id
	 * @param id
	 * @return
	 * @throws Exception
	 */
    boolean delete(Long id) throws Exception;
}