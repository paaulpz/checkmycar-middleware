package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.LineaPresupuestoDTO;

public interface LineaPresupuestoService {

    List<LineaPresupuestoDTO> findByPresupuesto(Long presupuestoId) throws Exception;

    Long create(LineaPresupuestoDTO linea) throws Exception;

    boolean delete(Long id) throws Exception;
}