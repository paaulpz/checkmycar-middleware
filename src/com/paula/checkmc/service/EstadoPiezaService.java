package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.EstadoPieza;

public interface EstadoPiezaService {
	
	
	/**
	 * Busca un estado de pieza por su id
	 * @param id
	 * @return
	 * @throws Exception 
	 */
   public  EstadoPieza findById(Long id) throws Exception;
    
    
    /**
	 * Busca todos los estados de pieza
	 * @return
	 */
   public  List<EstadoPieza> findAll() throws Exception;
}