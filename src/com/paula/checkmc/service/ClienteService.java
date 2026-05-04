package com.paula.checkmc.service;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;

public interface ClienteService {

	/**
	 * Busca un cliente por su ID.
	 * @param id
	 * @return
	 */
    Cliente findById(Long id);
    
    
    
    
     /**
	  * Busca clientes por criterios de búsqueda.
	  * @param criteria
	  * @return
	  */
      Results<ClienteDTO> findByCriteria(ClienteCriteria criteria, int from , int pageSize);
    
    
    /**
	 * Crea un nuevo cliente.
	 * @param cliente
	 * @return El ID del cliente creado.
	 */
    Cliente create(Cliente cliente);
    
    
    /**
     * Registra un nuevo cliente.
     * @param cliente
     * @return
     */
    public Long register(Cliente cliente);
    
          /**
	  * Actualiza un cliente existente.
	  * @param cliente
	  * @return true si la actualización fue exitosa, false de lo contrario.
	  */
      boolean update(Cliente cliente);
      
      
      
      /**
       * Elimina un cliente por su ID.
       * @param id
       * @return
       */

    boolean delete(Long id);




	  ClienteDTO login(String email, String password);
}