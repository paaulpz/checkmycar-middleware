package com.paula.checkmc.service.impl;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.PresupuestoDAO;
import com.paula.checkmc.model.Presupuesto;
import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.PresupuestoService;

public class PresupuestoServiceImpl implements PresupuestoService {
	
	private static final Logger logger = LogManager.getLogger(PresupuestoServiceImpl.class);

    private PresupuestoDAO dao = new PresupuestoDAO();

    @Override
    public PresupuestoDTO findById(Long id) throws Exception {

        Presupuesto p = dao.findById(id);

        if (p == null) return null;

        PresupuestoDTO dto = new PresupuestoDTO();

        dto.setId(p.getId());
        dto.setFecha(p.getFecha());
        dto.setPrecioFinal(p.getPrecioFinal());
        dto.setClienteId(p.getClienteId());
        dto.setEstadoPresupuestoId(p.getEstadoPresupuestoId());
        dto.setNombre(p.getNombre());

        return dto;
    }

    @Override
    public Results<PresupuestoDTO> findByCriteria(PresupuestoCriteria criteria, int from, int pageSize) {
        logger.info("criteria: {}", criteria);
        return dao.findByCriteria(criteria, from, pageSize);
    }


    @Override
    public Long create(PresupuestoDTO dto) throws Exception {

        if (!validar(dto)) return null;

        Presupuesto p = new Presupuesto();

        p.setFecha(dto.getFecha());
        p.setPrecioFinal(dto.getPrecioFinal());
        p.setClienteId(dto.getClienteId());
        p.setEstadoPresupuestoId(dto.getEstadoPresupuestoId());
        p.setNombre(dto.getNombre());

        return dao.create(p);
    }

    @Override
    public boolean update(PresupuestoDTO dto) throws Exception {

        if (!validar(dto)) return false;

        Presupuesto p = new Presupuesto();

        p.setId(dto.getId());
        p.setFecha(dto.getFecha());
        p.setPrecioFinal(dto.getPrecioFinal());
        p.setClienteId(dto.getClienteId());
        p.setEstadoPresupuestoId(dto.getEstadoPresupuestoId());
        p.setNombre(dto.getNombre());

        return dao.update(p);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return dao.delete(id);
    }

    private boolean validar(PresupuestoDTO p) {

        if (p == null) return false;

        if (p.getFecha() == null) return false;

        if (p.getFecha().isAfter(LocalDate.now())) return false;

        if (p.getClienteId() == null) return false;

        if (p.getEstadoPresupuestoId() == null) return false;

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) return false;

        if (p.getPrecioFinal() == null || p.getPrecioFinal() < 0) return false;

        return true;
    }
}