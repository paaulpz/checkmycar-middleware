package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Marca;

public interface MarcaService {
	
	
	/**
	 * 	Busca una marca por su id
	 * @param id
	 * @return
	 */
    Marca findById(Long id);

    
    /**
		 * 	Busca todas las marcas
		 */
    List<Marca> findAll();
}