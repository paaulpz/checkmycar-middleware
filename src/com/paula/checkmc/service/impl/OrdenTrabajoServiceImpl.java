package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.OrdenTrabajoDAO;
import com.paula.checkmc.model.OrdenTrabajo;
import com.paula.checkmc.model.OrdenTrabajoCriteria;
import com.paula.checkmc.model.OrdenTrabajoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.OrdenTrabajoService;
import com.paula.checkmc.util.JDBCUtils;

public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    private static final Logger logger = LogManager.getLogger(OrdenTrabajoServiceImpl.class);

    private OrdenTrabajoDAO ordenTrabajoDAO = new OrdenTrabajoDAO();

    @Override
    public OrdenTrabajoDTO findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            OrdenTrabajo ot = ordenTrabajoDAO.findById(c, id);

            return ot != null ? toDTO(ot) : null;

        } catch (Exception e) {

            logger.error("Error buscando orden trabajo {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public Results<OrdenTrabajoDTO> findByCriteria(OrdenTrabajoCriteria criteria, int from, int pageSize)
            throws Exception {

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Results<OrdenTrabajoDTO> results =
                    ordenTrabajoDAO.findByCriteria(c, criteria, from, pageSize);

            commit = true;

            return results;

        } catch (Exception e) {

            logger.error("Buscando {}: {}", criteria, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long create(OrdenTrabajoDTO dto) throws Exception {

        if (!validarOrden(dto)) {
            return null;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            OrdenTrabajo ot = toEntity(dto);

            Long id = ordenTrabajoDAO.create(c, ot);

            commit = true;

            return id;

        } catch (Exception e) {

            logger.error("Creando orden trabajo {}: {}", dto, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(OrdenTrabajoDTO dto) throws Exception {

        if (dto == null || dto.getId() == null || dto.getId() <= 0) {
            return false;
        }

        if (!validarOrden(dto)) {
            return false;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            OrdenTrabajo ot = toEntity(dto);

            boolean updated = ordenTrabajoDAO.update(c, ot);

            commit = true;

            return updated;

        } catch (Exception e) {

            logger.error("Actualizando orden trabajo {}: {}", dto, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {

        if (id == null || id <= 0) {
            return false;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            boolean deleted = ordenTrabajoDAO.delete(c, id);

            commit = true;

            return deleted;

        } catch (Exception e) {

            logger.error("Error eliminando orden trabajo {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

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

    private boolean validarOrden(OrdenTrabajoDTO orden) {

        if (orden == null) {
            return false;
        }

        if (orden.getDiagnostico() == null
                || orden.getDiagnostico().trim().isEmpty()) {

            return false;
        }

        if (orden.getFechaInicio() == null) {
            return false;
        }

        if (orden.getFechaFin() != null
                && orden.getFechaFin().isBefore(orden.getFechaInicio())) {

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