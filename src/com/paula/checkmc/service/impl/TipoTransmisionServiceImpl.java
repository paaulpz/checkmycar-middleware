package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.TipoTransmisionDAO;
import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.TipoTransmisionService;

public class TipoTransmisionServiceImpl implements TipoTransmisionService {

    private TipoTransmisionDAO dao = new TipoTransmisionDAO();

  

    @Override
    public List<TipoTransmision> findAll() {

        return dao.findAll();
    }



	@Override
	public TipoTransmision findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}