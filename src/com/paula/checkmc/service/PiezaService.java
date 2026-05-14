package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;

public interface PiezaService {
	
	
	
	/**
	 * Busca una pieza por su ID.
	 * @param id
	 * @return
	 */
    public PiezaDTO findById(Long id);
    
    
    /**
	 * Busca todas las piezas.
	 * @return
	 */
    public List<PiezaDTO> findAll();
    
    
    /**	 * Busca piezas que cumplan con ciertos criterios.
	 * @param criteria
	 */
    public List<PiezaDTO> findByCriteria(PiezaCriteria criteria, int from, int pageSize);

    /**	 * Crea una nueva pieza.
	 * @param pieza
	 * @return El ID de la pieza creada.
	 */
    
}