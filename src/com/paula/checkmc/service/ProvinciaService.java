package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Provincia;

public interface ProvinciaService {
	
/**
 * Busca una provincia por su ID.
 * @param id
 * @return
 */
    public Provincia findById(Long id)throws Exception;
    
    /**
	 * Busca todas las provincias.
	 * @return
	 */

    public List<Provincia> findAll() throws Exception;
    
    /**
	 * Busca las provincias por el ID del país.
	 * @param paisId
	 */


    public List<Provincia> findByPais(Long paisId) throws Exception;
}