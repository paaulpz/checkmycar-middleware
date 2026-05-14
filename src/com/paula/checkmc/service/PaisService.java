package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Pais;

public interface PaisService {
	
	/**
	 * Busca un país por su ID.
	 * @param id
	 * @return
	 * @throws Exception 
	 */

    public Pais findById(Long id) throws Exception;
    
    
	/**
	 * Busca todos los países disponibles.
	 * @return
	 * @throws Exception 
	 */
    public List<Pais> findAll() throws Exception;
}