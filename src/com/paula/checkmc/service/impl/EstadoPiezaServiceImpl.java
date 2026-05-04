package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.EstadoPiezaDAO;
import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.service.EstadoPiezaService;

public class EstadoPiezaServiceImpl implements EstadoPiezaService {

    private EstadoPiezaDAO dao = new EstadoPiezaDAO();

    @Override
    public EstadoPieza findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<EstadoPieza> findAll() {

        return dao.findAll();
    }
}