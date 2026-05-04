package com.paula.checkmc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.OrdenTrabajoDAO;
import com.paula.checkmc.model.OrdenTrabajo;
import com.paula.checkmc.model.OrdenTrabajoCriteria;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.OrdenTrabajoService;

public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    private static final Logger logger = LogManager.getLogger(OrdenTrabajoServiceImpl.class);

    private OrdenTrabajoDAO dao = new OrdenTrabajoDAO();

    @Override
    public OrdenTrabajoDTO findById(Long id) {

        logger.debug("Buscando orden trabajo id: {}", id);

        try {
            OrdenTrabajo ot = dao.findById(id);

            return ot != null ? toDTO(ot) : null;

        } catch (Exception e) {
            logger.error("Error en findById: {}", id, e);
        }

        return null;
    }

    @Override
    public Results<OrdenTrabajoDTO> findByCriteria(OrdenTrabajoCriteria criteria, int from, int pageSize) {
        logger.info("criteria: {}", criteria);
        return dao.findByCriteria(criteria, from, pageSize);
    }

    @Override
    public Long create(OrdenTrabajoDTO dto) {

        logger.debug("Creando orden trabajo: {}", dto);

        try {
            if (!validarOrden(dto)) return null;

            OrdenTrabajo ot = toEntity(dto);

            return dao.create(ot);

        } catch (Exception e) {
            logger.error("Error creando orden trabajo", e);
        }

        return null;
    }

    @Override
    public boolean update(OrdenTrabajoDTO dto) {

        logger.debug("Actualizando orden trabajo: {}", dto);

        try {
            if (!validarOrden(dto)) return false;

            OrdenTrabajo ot = toEntity(dto);

            return dao.update(ot);

        } catch (Exception e) {
            logger.error("Error actualizando orden trabajo", e);
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {

        logger.warn("Eliminando orden trabajo id: {}", id);

        try {
            return dao.delete(id);
        } catch (Exception e) {
            logger.error("Error eliminando orden trabajo: {}", id, e);
        }

        return false;
    }

    // 🔹 MAPPERS

    private OrdenTrabajoDTO toDTO(OrdenTrabajo ot) {
        OrdenTrabajoDTO dto = new OrdenTrabajoDTO();

        dto.setId(ot.getId());
        dto.setDiagnostico(ot.getDiagnostico());
        dto.setFechaInicio(ot.getFechaInicio());
        dto.setFechaFin(ot.getFechaFin());
        dto.setPresupuestoId(ot.getPresupuestoId());
        dto.setCocheId(ot.getCocheId());

        return dto;
    }

    private OrdenTrabajo toEntity(OrdenTrabajoDTO dto) {
        OrdenTrabajo ot = new OrdenTrabajo();

        ot.setId(dto.getId());
        ot.setDiagnostico(dto.getDiagnostico());
        ot.setFechaInicio(dto.getFechaInicio());
        ot.setFechaFin(dto.getFechaFin());
        ot.setPresupuestoId(dto.getPresupuestoId());
        ot.setCocheId(dto.getCocheId());

        return ot;
    }

    // 🔹 VALIDACIÓN

    private boolean validarOrden(OrdenTrabajoDTO orden) {

        if (orden == null) return false;

        if (orden.getDiagnostico() == null || orden.getDiagnostico().trim().isEmpty()) {
            return false;
        }

        if (orden.getFechaInicio() == null) {
            return false;
        }

        if (orden.getFechaFin() != null &&
            orden.getFechaFin().isBefore(orden.getFechaInicio())) {
            return false;
        }

        if (orden.getPresupuestoId() == null) {
            return false;
        }

        if (orden.getCocheId() == null) {
            return false;
        }

        return true;
    }
}