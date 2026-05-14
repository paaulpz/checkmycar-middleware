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
   public Cliente findById(Long id) throws Exception;;
    
    
    
    
     /**
	  * Busca clientes por criterios de búsqueda.
	  * @param criteria
	  * @return
	  */
     public Results<ClienteDTO> findByCriteria(ClienteCriteria criteria, int from , int pageSize) 	throws Exception;;
    
    
    /**
	 * Crea un nuevo cliente.
	 * @param cliente
	 * @return El ID del cliente creado.
	 */
   public  Long create(Cliente cliente) 	throws Exception;;
    
    
    /**
     * Registra un nuevo cliente.
     * @param cliente
     * @return
     */
    public Long register(Cliente cliente) throws Exception;;
    
          /**
	  * Actualiza un cliente existente.
	  * @param cliente
	  * @return true si la actualización fue exitosa, false de lo contrario.
	  */
  public boolean update(Cliente cliente)	throws Exception;
      
      
      
      /**
       * Elimina un cliente por su ID.
       * @param id
       * @return
       */

  public boolean delete(Long id)throws Exception;



/**
 * Inicia sesión para un cliente dado su correo electrónico y contraseña.
 * @param email
 * @param password
 * @return
 * @throws Exception
 */
	public ClienteDTO login(String email, String password) throws Exception;
}