package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.PaisDAO;
import com.paula.checkmc.model.Pais;
import com.paula.checkmc.service.PaisService;

public class PaisServiceImpl implements PaisService {

    private PaisDAO dao = new PaisDAO();

    @Override
    public Pais findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<Pais> findAll() {
        return dao.findAll();
    }
}