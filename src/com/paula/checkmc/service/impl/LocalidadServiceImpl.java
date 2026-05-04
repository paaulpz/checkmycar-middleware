package com.paula.checkmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.dao.LocalidadDAO;
import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.service.LocalidadService;

public class LocalidadServiceImpl implements LocalidadService {

    private LocalidadDAO dao = new LocalidadDAO();

    @Override
    public Localidad findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

    @Override
    public List<Localidad> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Localidad> findByProvincia(Long provinciaId) {

        if (provinciaId == null || provinciaId <= 0) {
            return new ArrayList<>(); // nunca null
        }

        return dao.findByProvince(provinciaId);
    }

    @Override
    public List<Localidad> findByNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) return null;

        return dao.findByNombre(nombre);
    }
}