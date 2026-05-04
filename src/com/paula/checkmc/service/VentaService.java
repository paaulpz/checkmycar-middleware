package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;

public interface VentaService {

    VentaDTO findById(Long id) throws Exception;

    List<VentaDTO> findByCriteria(VentaCriteria criteria, int from , int pageSize) throws Exception;

    Long create(VentaDTO venta) throws Exception;

    boolean update(VentaDTO venta) throws Exception;

    boolean delete(Long id) throws Exception;
}