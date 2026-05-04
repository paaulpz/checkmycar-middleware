package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.EstadoPresupuestoDAO;
import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.service.EstadoPresupuestoService;

public class EstadoPresupuestoServiceImpl implements EstadoPresupuestoService {

    private EstadoPresupuestoDAO dao = new EstadoPresupuestoDAO();

    @Override
    public EstadoPresupuesto findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<EstadoPresupuesto> findAll() throws Exception {
        return dao.findAll();
    }
}