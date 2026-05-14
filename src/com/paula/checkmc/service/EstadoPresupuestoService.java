package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.EstadoPresupuesto;

public interface EstadoPresupuestoService {

	
	/**
	 * Busca un estado de presupuesto por su id
	 * @param id
	 * @return
	 * @throws Exception
	 */
   public  EstadoPresupuesto findById(Long id) throws Exception;
   
   
   /**
    * Busca todos los estados de presupuesto
    * @return
    * @throws Exception
    */
   public  List<EstadoPresupuesto> findAll() throws Exception;

}