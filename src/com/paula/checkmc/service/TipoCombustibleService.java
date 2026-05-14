package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.TipoCombustible;

public interface TipoCombustibleService {
	
	/**
	 * Busca un tipo de combustible por su ID.
	 *
	 * @param id El ID del tipo de combustible a buscar.
	 * @return El tipo de combustible encontrado, o null si no se encuentra.
	 */

    public TipoCombustible findById(Long id) throws Exception;

    
    
    /**
	 * Busca todos los tipos de combustible disponibles.
	 *
	 * @return Una lista de todos los tipos de combustible.
	 */
    public List<TipoCombustible> findAll() throws Exception;
}