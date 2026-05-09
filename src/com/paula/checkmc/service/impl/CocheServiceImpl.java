package com.paula.checkmc.service.impl;

import com.paula.checkmc.dao.CocheDAO;
import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CocheService;

public class CocheServiceImpl implements CocheService {

    private CocheDAO dao = new CocheDAO();

    @Override
    public Coche findById(Long id) {
        if (id == null || id <= 0) return null;
        return dao.findById(id);
    }

    @Override
    public Results<CocheDTO> findByCriteria(CocheCriteria criteria, int from, int pageSize) {
        return dao.findByCriteria(criteria, from, pageSize);
    }

    @Override
    public Long create(Coche coche) {
        return dao.create(coche);
    }

    @Override
    public boolean update(Coche coche) {
        if (coche.getId() == null || coche.getId() <= 0) return false;
        return dao.update(coche);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null || id <= 0) return false;
        return dao.delete(id);
    }
}