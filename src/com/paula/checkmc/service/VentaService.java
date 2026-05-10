package com.paula.checkmc.service;

import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;

public interface VentaService {

	  public VentaDTO findById(Long id) throws Exception;

  public Results<VentaDTO> findByCriteria(VentaCriteria criteria, int from , int pageSize) throws Exception;

  public Venta create(VentaDTO venta) throws Exception;

  public boolean update(VentaDTO venta) throws Exception;

  public boolean delete(Long id) throws Exception;
}