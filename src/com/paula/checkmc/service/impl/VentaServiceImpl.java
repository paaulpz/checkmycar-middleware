package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.VentaDAO;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;

public class VentaServiceImpl implements VentaService {

    private VentaDAO dao = new VentaDAO();

    @Override
    public VentaDTO findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public List<VentaDTO> findByCriteria(VentaCriteria criteria, int from , int pageSize) throws Exception {
        return dao.findByCriteria(criteria, from, pageSize);
    }

    

    @Override
    public boolean delete(Long id) throws Exception {
        return dao.delete(id);
    }

	@Override
	public Long create(VentaDTO venta) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(VentaDTO venta) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}