package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.LineaPresupuestoDTO;

public interface LineaPresupuestoService {
	
	
	/**
	 * Busca una línea de presupuesto por su ID.
	 * @param presupuestoId
	 * @return
	 * @throws Exception
	 */
    public List<LineaPresupuestoDTO> findByPresupuesto(Long presupuestoId) throws Exception;
    
    
     /**
      * Crea una nueva línea de presupuesto.
      * @param linea
      * @return
      * @throws Exception
      */
    public Long create(LineaPresupuestoDTO linea) throws Exception;
    
    
     /**
	  * Elimina una línea de presupuesto por su ID.
	  * @param id
	  * @return
	  * @throws Exception
	  */
   public  boolean delete(Long id) throws Exception;
}