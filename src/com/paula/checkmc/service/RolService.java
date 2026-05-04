package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Rol;

public interface RolService {
	
	
	/**	 * Busca un rol por su ID.
	 *
	 * @param id El ID del rol a buscar.
	 * @return El rol encontrado o null si no existe.
	 */

    Rol findById(Long id);
    
    
    /**    * Busca todos los roles disponibles.
	 *
	 * @return Una lista de todos los roles.
	 */

    List<Rol> findAll();
}