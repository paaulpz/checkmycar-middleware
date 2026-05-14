package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Marca;

public interface MarcaService {
	
	
	/**
	 * 	Busca una marca por su id
	 * @param id
	 * @return
	 */
   public  Marca findById(Long id) throws Exception;

    
   /**
    * Busca todas las marcas
    * @return
    * @throws Exception
    */
    public List<Marca> findAll() throws Exception;
}