package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.Genero;

public interface GeneroService {
	
	
	
	/**
	 * Busca un género por su ID.
	 * @param id
	 * @return
	 */
    Genero findById(Long id);
    
    
    /**	 * Busca todos los géneros disponibles.
	 * @return 
	 */
    List<Genero> findAll();
}