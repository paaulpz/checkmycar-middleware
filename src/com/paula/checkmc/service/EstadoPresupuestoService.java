package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.EstadoPresupuesto;

public interface EstadoPresupuestoService {

    EstadoPresupuesto findById(Long id);

    List<EstadoPresupuesto> findAll() throws Exception;

}