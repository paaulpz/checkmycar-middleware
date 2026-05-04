package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.EstadoPieza;

public interface EstadoPiezaService {
	
	
	/**
	 * Busca un estado de pieza por su id
	 * @param id
	 * @return
	 */
    EstadoPieza findById(Long id);
    
    
    /**
	 * Busca todos los estados de pieza
	 * @return
	 */
    List<EstadoPieza> findAll();
}