package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.TipoMotorDAO;
import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.service.TipoMotorService;

public class TipoMotorServiceImpl implements TipoMotorService {

    private TipoMotorDAO dao = new TipoMotorDAO();

  

    @Override
    public List<TipoMotor> findAll() {

        return dao.findAll();
    }



	@Override
	public TipoMotor findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	
}