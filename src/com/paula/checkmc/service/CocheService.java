package com.paula.checkmc.service;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;

public interface CocheService {
	
	
	
	/**
	 * Busca un coche por su ID.
	 * @param id
	 * @return
	 */
    Coche findById(Long id);
    
  
    
    /**
	 * Busca coches por criterios de búsqueda.
	 * @param criteria
	 * @return
	 */ 
    Results<CocheDTO> findByCriteria(CocheCriteria criteria, int from, int pageSize);
    
    
    /**
     * Crea un nuevo coche.
     * @param coche
     * @return
     */
    Long create(Coche coche);
    
    
    /**
	 * Actualiza un coche existente.
	 * @param coche
	 * @return
	 */
    boolean update(Coche coche);

    
    /**
     * Elimina un coche por su ID.
     * @param id
     * @return
     */
    boolean delete(Long id);
}