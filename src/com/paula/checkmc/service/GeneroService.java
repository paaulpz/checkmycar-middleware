package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Genero;

public interface GeneroService {
	
	
	
	/**
	 * Busca un género por su ID.
	 * @param id
	 * @return
	 */
  public Genero findById(Long id) throws Exception;
    
    
    /**	 * Busca todos los géneros disponibles.
	 * @return 
	 */
   public  List<Genero> findAll() throws Exception;
}