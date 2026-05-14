package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Rol;

public interface RolService {
	
	
	/**	 * Busca un rol por su ID.
	 *
	 * @param id El ID del rol a buscar.
	 * @return El rol encontrado o null si no existe.
	 */

    public Rol findById(Long id) throws Exception;
    
    
    /**    * Busca todos los roles disponibles.
	 *
	 * @return Una lista de todos los roles.
	 */

    public List<Rol> findAll() throws Exception;
}