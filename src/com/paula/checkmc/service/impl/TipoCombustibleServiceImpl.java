package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.TipoCombustibleDAO;
import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.service.TipoCombustibleService;

public class TipoCombustibleServiceImpl implements TipoCombustibleService {

    private TipoCombustibleDAO dao = new TipoCombustibleDAO();

   
    @Override
    public List<TipoCombustible> findAll() {

        return dao.findAll();
    }


	@Override
	public TipoCombustible findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}