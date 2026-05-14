package com.paula.checkmc.service;

import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;

public interface PresupuestoService {

    public PresupuestoDTO findById(Long id) throws Exception;

    public Results<PresupuestoDTO> findByCriteria(PresupuestoCriteria criteria, int from , int pageSize) throws Exception;

    public Long create(PresupuestoDTO presupuesto) throws Exception;

    public boolean update(PresupuestoDTO presupuesto) throws Exception;

    public boolean delete(Long id) throws Exception;
}