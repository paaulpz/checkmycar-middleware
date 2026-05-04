package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.EstadoCitaDAO;
import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.service.EstadoCitaService;

public class EstadoCitaServiceImpl implements EstadoCitaService {

    private EstadoCitaDAO dao = new EstadoCitaDAO();

    @Override
    public EstadoCita findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<EstadoCita> findAll() {

        return dao.findAll();
    }
}