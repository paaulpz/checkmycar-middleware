package com.paula.checkmc.service;
import java.util.List;

import com.paula.checkmc.model.Modelo;

public interface ModeloService {
	
	
    
    /**
     * Obtiene los modelos de coches asociados a una marca específica.
     * @param marcaId
     * @return
     */
   public  List<Modelo> findByMarca(Long marcaId);
}