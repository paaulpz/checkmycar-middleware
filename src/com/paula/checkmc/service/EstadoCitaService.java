package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.EstadoCita;

public interface EstadoCitaService {
	
	
	
	/**	 * Busca un estado de cita por su ID.
	 *
	 * @param id El ID del estado de cita a buscar.
	 * @return El estado de cita encontrado, o null si no se encuentra.
	 */
    EstadoCita findById(Long id);
    
    /**
	 * Busca todos los estados de cita disponibles.
	 *
	 * @return Una lista de todos los estados de cita.
	 */

    List<EstadoCita> findAll();
}