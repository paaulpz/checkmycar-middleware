package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.PresupuestoEmpleadoDAO;
import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.service.PresupuestoEmpleadoService;

public class PresupuestoEmpleadoServiceImpl implements PresupuestoEmpleadoService {

    private PresupuestoEmpleadoDAO dao = new PresupuestoEmpleadoDAO();

    @Override
    public boolean create(PresupuestoEmpleado pe) {

        if (pe == null) return false;
        if (pe.getEmpleadoId() == null || pe.getEmpleadoId() <= 0) return false;
        if (pe.getPresupuestoId() == null || pe.getPresupuestoId() <= 0) return false;

        return dao.create(pe);
    }

    @Override
    public boolean delete(Long empleadoId, Long presupuestoId) {

        if (empleadoId == null || empleadoId <= 0) return false;
        if (presupuestoId == null || presupuestoId <= 0) return false;

        return dao.delete(empleadoId, presupuestoId);
    }

    @Override
    public List<Empleado> findPresupuestosByEmpleado(Long empleadoId) {

        if (empleadoId == null || empleadoId <= 0) return null;

        return dao.findPresupuestosByEmpleado(empleadoId);
    }

	@Override
	public List<Long> findEmpleadosByPresupuesto(Long presupuestoId) {
		// TODO Auto-generated method stub
		return null;
	}

  
}