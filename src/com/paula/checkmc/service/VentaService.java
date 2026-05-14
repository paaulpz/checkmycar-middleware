package com.paula.checkmc.service;

import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;

public interface VentaService {
	
	
/**
 * Busca una venta por su ID.
 * @param id
 * @return
 * @throws Exception
 */
  public VentaDTO findById(Long id) throws Exception;
  
  /**
   * Busca todas las ventas que cumplen con los criterios especificados.
   * @param criteria
   * @param from
   * @param pageSize
   * @return
   * @throws Exception
   */
  public Results<VentaDTO> findByCriteria(VentaCriteria criteria, int from , int pageSize) throws Exception;

  
  /**
   * Crea una nueva venta.
   * @param venta
   * @return
   * @throws Exception
   */
  public Venta create(VentaDTO venta) throws Exception;
  
  
  /**
   * Actualiza una venta existente.
   * @param venta
   * @return
   * @throws Exception
   */
  public boolean update(VentaDTO venta) throws Exception;
  
  
  /**
   * Elimina una venta por su ID.
   * @param id
   * @return
   * @throws Exception
   */
  public boolean delete(Long id) throws Exception;
}