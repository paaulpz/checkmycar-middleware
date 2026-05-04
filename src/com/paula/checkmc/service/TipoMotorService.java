package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.TipoMotor;

public interface TipoMotorService {
	
	
	/**	 * Busca un tipo de motor por su ID.
	 *
	 * @param id El ID del tipo de motor a buscar.
	 * @return El tipo de motor encontrado, o null si no se encuentra.
	 */

    TipoMotor findById(Long id);
    
    
    /**	 * Busca todos los tipos de motor disponibles.
     * 
     * @return
     */

    List<TipoMotor> findAll();
}