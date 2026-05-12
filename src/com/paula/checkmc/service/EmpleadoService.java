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

   public  Empleado findById(Long id);
    

    
    /**
     * Busca empleados por criterios de búsqueda.
     * @param criteria
     * @return
     */

 public  Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria , int from, int pageSize);
    
 public Long register(Empleado empleado);
    /**
	 * Crea un nuevo empleado.
	 * @param empleado
	 * @return El ID del empleado creado.
	 */

   public Empleado create(Empleado empleado);
    
    /**
     * Actualiza un empleado existente.
     * @param empleado
     * @return
     */

   public  boolean update(Empleado empleado);
    
    /**
	 * Elimina un empleado por su ID.
	 * @param id
	 * @return
	 */
  public   boolean delete(Long id);


public EmpleadoDTO login(String dni, String password, Long rolId);
}