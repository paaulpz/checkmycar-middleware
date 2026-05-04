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

    Localidad findById(Long id);
    
    /**
	 * Busca todas las localidades disponibles.
	 *
	 * @return Una lista de todas las localidades.
	 */

    List<Localidad> findAll();
    
    /**
     * Busca las localidades que pertenecen a una provincia específica.
     * @param provinceId
     * @return
     */

    List<Localidad> findByProvincia(Long provinciaId);
    
    /**
	 * Busca las localidades que coinciden con un nombre específico.
	 * @param nombre
	 * @return
	 */

    List<Localidad> findByNombre(String nombre);

	
}