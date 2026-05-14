package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Localidad;

public interface LocalidadService {
	
	/**
	 * Busca una localidad por su ID.
	 *
	 * @param id El ID de la localidad a buscar.
	 * @return La localidad encontrada o null si no existe.
	 */

   public  Localidad findById(Long id) throws Exception;
    
    /**
	 * Busca todas las localidades disponibles.
	 *
	 * @return Una lista de todas las localidades.
	 */

    public List<Localidad> findAll() throws Exception;
    
    /**
     * Busca las localidades que pertenecen a una provincia específica.
     * @param provinceId
     * @return
     */

   public  List<Localidad> findByProvincia(Long provinciaId) throws Exception;
    
    /**
	 * Busca las localidades que coinciden con un nombre específico.
	 * @param nombre
	 * @return
	 */

    public List<Localidad> findByNombre(String nombre)throws Exception;

	
}