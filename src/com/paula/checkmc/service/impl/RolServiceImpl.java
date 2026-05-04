package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.RolDAO;
import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.RolService;

public class RolServiceImpl implements RolService {

    private RolDAO dao = new RolDAO();

   

    @Override
    public List<Rol> findAll() {

        return dao.findAll();
    }



	@Override
	public Rol findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
