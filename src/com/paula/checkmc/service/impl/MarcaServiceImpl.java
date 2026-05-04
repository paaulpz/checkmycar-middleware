package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.MarcaDAO;
import com.paula.checkmc.model.Marca;
import com.paula.checkmc.service.MarcaService;

public class MarcaServiceImpl implements MarcaService {

    private MarcaDAO dao = new MarcaDAO();

    @Override
    public Marca findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<Marca> findAll() {

        return dao.findAll();
    }
}