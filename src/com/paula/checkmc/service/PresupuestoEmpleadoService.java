package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;

public interface PresupuestoEmpleadoService {
	
	
	/**
	 * 	Crea una asociación entre un presupuesto y un empleado.
	 * @param pe
	 * @return true si la asociación se creó correctamente, false en caso contrario.
	 * @throws Exception 
	 */
   public  boolean create(PresupuestoEmpleado pe) throws Exception;

    /**
     * Elimina la asociación entre un presupuesto y un empleado.
     * @param empleadoId
     * @param presupuestoId
     * @return
     * @throws Exception 
     */
    public boolean delete(Long empleadoId, Long presupuestoId) throws Exception;
    
    
    /**
	 * Busca los IDs de los presupuestos asociados a un empleado.
	 * @param empleadoId
	 * @return Lista de IDs de presupuestos asociados al empleado.
	 */
    public List<Empleado> findPresupuestosByEmpleado(Long empleadoId) throws Exception;
    
    
    /**
     * Busca los IDs de los empleados asociados a un presupuesto.
     * @param presupuestoId
     * @return
     */
    public List<Long> findEmpleadosByPresupuesto(Long presupuestoId) throws Exception;
}