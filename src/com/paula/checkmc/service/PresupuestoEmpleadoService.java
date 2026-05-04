package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;

public interface PresupuestoEmpleadoService {
	
	
	/**
	 * 	Crea una asociación entre un presupuesto y un empleado.
	 * @param pe
	 * @return true si la asociación se creó correctamente, false en caso contrario.
	 */
    boolean create(PresupuestoEmpleado pe);

    /**
     * Elimina la asociación entre un presupuesto y un empleado.
     * @param empleadoId
     * @param presupuestoId
     * @return
     */
    boolean delete(Long empleadoId, Long presupuestoId);
    
    
    /**
	 * Busca los IDs de los presupuestos asociados a un empleado.
	 * @param empleadoId
	 * @return Lista de IDs de presupuestos asociados al empleado.
	 */
    List<Empleado> findPresupuestosByEmpleado(Long empleadoId);
    
    
    /**
     * Busca los IDs de los empleados asociados a un presupuesto.
     * @param presupuestoId
     * @return
     */
    List<Long> findEmpleadosByPresupuesto(Long presupuestoId);
}