package com.paula.checkmc.service.impl;

import com.paula.checkmc.dao.CitaDAO;
import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CitaService;

public class CitaServiceImpl implements CitaService {

    private CitaDAO dao;

    public CitaServiceImpl() {

        dao = new CitaDAO();
    }

    @Override
    public Results<CitaDTO> findByCriteria(CitaCriteria criteria,
                                           int from,
                                           int pageSize) {

        return dao.findByCriteria(criteria, from, pageSize);
    }

    @Override
    public Long create(Cita cita) {

        return dao.create(cita);
    }

    @Override
    public boolean update(Cita cita) {

        return dao.update(cita);
    }

    @Override
    public boolean delete(Long id) throws Exception {

        return dao.delete(id);
    }

    @Override
    public boolean existeCitaEnFecha(java.time.LocalDateTime fecha) {

        return dao.existeCitaEnFecha(fecha);
    }
}