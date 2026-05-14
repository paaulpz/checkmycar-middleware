package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.TipoMotor;

public interface TipoMotorService {
	
	
	/**	 * Busca un tipo de motor por su ID.
	 *
	 * @param id El ID del tipo de motor a buscar.
	 * @return El tipo de motor encontrado, o null si no se encuentra.
	 */

    public TipoMotor findById(Long id) throws Exception;
    
    
    /**	 * Busca todos los tipos de motor disponibles.
     * 
     * @return
     */

    public List<TipoMotor> findAll() throws Exception;
}