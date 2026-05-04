package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.GeneroDAO;
import com.paula.checkmc.model.Genero;
import com.paula.checkmc.service.GeneroService;

public class GeneroServiceImpl implements GeneroService {

    private GeneroDAO dao = new GeneroDAO();

    @Override
    public Genero findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<Genero> findAll() {

        return dao.findAll();
    }
}