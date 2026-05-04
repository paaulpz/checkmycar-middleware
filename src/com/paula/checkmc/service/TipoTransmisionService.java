package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.TipoTransmision;

public interface TipoTransmisionService {
	
	
	/**	 * Busca un tipo de transmisión por su ID.
	 *
	 * @param id El ID del tipo de transmisión a buscar.
	 * @return El tipo de transmisión encontrado, o null si no se encuentra.
	 */

    TipoTransmision findById(Long id);
    
    
    /**	 * Busca todos los tipos de transmisión disponibles.
     * 
     * @return
     */

    List<TipoTransmision> findAll();
}