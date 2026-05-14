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
   public Coche findById(Long id) throws Exception;
    
  
    
    /**
	 * Busca coches por criterios de búsqueda.
	 * @param criteria
	 * @return
	 */ 
   public Results<CocheDTO> findByCriteria(CocheCriteria criteria, int from, int pageSize)
    	throws Exception;
    
    
    /**
     * Crea un nuevo coche.
     * @param coche
     * @return
     */
   public  Long create(Coche coche) throws Exception;
    
    
    /**
	 * Actualiza un coche existente.
	 * @param coche
	 * @return
	 */
   public boolean update(Coche coche) throws Exception;

    
    /**
     * Elimina un coche por su ID.
     * @param id
     * @return
     */
   public boolean delete(Long id) throws Exception;
}