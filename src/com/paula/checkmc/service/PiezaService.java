package com.paula.checkmc.service;

import java.util.List;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.model.PiezaCriteria;

public interface PiezaService {
	
	
	
	/**
	 * Busca una pieza por su ID.
	 * @param id
	 * @return
	 */
    PiezaDTO findById(Long id);
    
    
    /**
	 * Busca todas las piezas.
	 * @return
	 */
    List<PiezaDTO> findAll();
    
    
    /**	 * Busca piezas que cumplan con ciertos criterios.
	 * @param criteria
	 */
    List<PiezaDTO> findByCriteria(PiezaCriteria criteria, int from, int pageSize);

    /**	 * Crea una nueva pieza.
	 * @param pieza
	 * @return El ID de la pieza creada.
	 */
    Long create(PiezaDTO pieza);
    
    
    /**	 * Actualiza una pieza existente.
     * @param pieza
     * @return
     */
    boolean update(PiezaDTO pieza);
    
    /**	 * Elimina una pieza por su ID.
	 * @param id
	 * @return
	 */

    boolean delete(Long id);
}