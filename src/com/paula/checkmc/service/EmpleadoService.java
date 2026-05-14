package com.paula.checkmc.service;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;

public interface EmpleadoService {
	
	
	/**
	 * Busca un empleado por su ID.
	 * @param id
	 * @return
	 */

   public  Empleado findById(Long id) throws Exception;
    

    
    /**
     * Busca empleados por criterios de búsqueda.
     * @param criteria
     * @return
     */

 public  Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria , int from, int pageSize) throws Exception;
    
 
 /**
	 * Registra un nuevo empleado.
	 * @param empleado
	 * @return El ID del empleado registrado.
	 */
 public Long register(Empleado empleado) throws Exception;
 
    /**
	 * Crea un nuevo empleado.
	 * @param empleado
	 * @return El ID del empleado creado.
	 */

   public Long create(Empleado empleado) throws Exception;
    
    /**
     * Actualiza un empleado existente.
     * @param empleado
     * @return
     */

   public  boolean update(Empleado empleado) throws Exception;
    
    /**
	 * Elimina un empleado por su ID.
	 * @param id
	 * @return
	 */
  public   boolean delete(Long id) throws Exception;


  /**
   * Inicia sesión para un empleado dado su DNI, contraseña y rol.
   * @param dni
   * @param password
   * @param rolId
   * @return
   */
public EmpleadoDTO login(String dni, String password, Long rolId) throws Exception ;


}