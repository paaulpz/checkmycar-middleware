package com.paula.checkmc.service.impl;
import java.util.List;

import com.paula.checkmc.dao.ModeloDAO;
import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.service.ModeloService;

public class ModeloServiceImpl implements ModeloService {
    private ModeloDAO dao = new ModeloDAO();
 

    @Override
    public List<Modelo> findByMarca(Long marcaId) {
        if (marcaId == null || marcaId <= 0) return null;
        return dao.findByMarca(marcaId);
    }
}