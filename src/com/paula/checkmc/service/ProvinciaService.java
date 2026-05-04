package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.Provincia;

public interface ProvinciaService {
	
/**
 * Busca una provincia por su ID.
 * @param id
 * @return
 */
    Provincia findById(Long id);
    
    /**
	 * Busca todas las provincias.
	 * @return
	 */

    List<Provincia> findAll();
    
    /**
	 * Busca las provincias por el ID del país.
	 * @param paisId
	 */


    List<Provincia> findByPais(Long paisId);
}