package com.paula.checkmc.service;

import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;

public interface PresupuestoService {

    PresupuestoDTO findById(Long id) throws Exception;

    Results<PresupuestoDTO> findByCriteria(PresupuestoCriteria criteria, int from , int pageSize) throws Exception;

    Long create(PresupuestoDTO presupuesto) throws Exception;

    boolean update(PresupuestoDTO presupuesto) throws Exception;

    boolean delete(Long id) throws Exception;
}