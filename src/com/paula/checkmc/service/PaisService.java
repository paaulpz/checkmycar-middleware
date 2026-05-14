package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Pais;

public interface PaisService {
	
	/**
	 * Busca un país por su ID.
	 * @param id
	 * @return
	 */

    public Pais findById(Long id);
    
    
	/**
	 * Busca todos los países disponibles.
	 * @return
	 */
    public List<Pais> findAll();
}