package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.ProvinciaDAO;
import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService {

    private ProvinciaDAO dao = new ProvinciaDAO();

    @Override
    public Provincia findById(Long id) {

        if (id == null || id <= 0) return null;

        return dao.findById(id);
    }

  

    @Override
    public List<Provincia> findByPais(Long paisId) {

        if (paisId == null || paisId <= 0) return null;

        return dao.findByPais(paisId);
    }



	@Override
	public List<Provincia> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}