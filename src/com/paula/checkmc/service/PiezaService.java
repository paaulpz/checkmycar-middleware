package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;

public interface PiezaService {
	
	
	
	/**
	 * Busca una pieza por su ID.
	 * @param id
	 * @return
	 * @throws Exception 
	 */
    public PiezaDTO findById(Long id) throws Exception;
    
    
    /**
	 * Busca todas las piezas.
	 * @return
	 */
    public List<PiezaDTO> findAll() throws Exception;
    
    
    /**	 * Busca piezas que cumplan con ciertos criterios.
	 * @param criteria
	 */
    public List<PiezaDTO> findByCriteria(PiezaCriteria criteria, int from, int pageSize) throws Exception;

   
    
}