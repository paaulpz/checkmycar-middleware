package com.paula.checkmc.service;

import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.Results;

public interface CitaService {
	
	/**
	 * Busca una cita por su ID.
	 * @param criteria
	 * @return
	 * @throws Exception
	 */

   Results<CitaDTO> findByCriteria(CitaCriteria criteria , int from, int pageSize);
    
    /**
	 * Crea una nueva cita.
	 * @param cita
	 * @return El ID de la cita creada.
	 * @throws Exception
	 */

    Long create(Cita cita) throws Exception;
    
    /**
     * Actualiza una cita existente.
     * @param id
     * @return
     * @throws Exception
     */

    boolean delete(Long id) throws Exception;
    
    
    boolean update(Cliente cliente);
}