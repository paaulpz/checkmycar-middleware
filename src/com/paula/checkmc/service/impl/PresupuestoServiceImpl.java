package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.PresupuestoDAO;
import com.paula.checkmc.model.Presupuesto;
import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.PresupuestoService;
import com.paula.checkmc.util.JDBCUtils;

public class PresupuestoServiceImpl implements PresupuestoService {

    private static final Logger logger = LogManager.getLogger(PresupuestoServiceImpl.class);

    private PresupuestoDAO presupuestoDAO = new PresupuestoDAO();

    @Override
    public PresupuestoDTO findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            Presupuesto p = presupuestoDAO.findById(c, id);

            if (p == null) {
                return null;
            }

            PresupuestoDTO dto = new PresupuestoDTO();

            dto.setId(p.getId());
            dto.setFecha(p.getFecha());
            dto.setPrecioFinal(p.getPrecioFinal());
            dto.setClienteId(p.getClienteId());
            dto.setEstadoPresupuestoId(p.getEstadoPresupuestoId());
            dto.setNombre(p.getNombre());

            return dto;

        } catch (Exception e) {

            logger.error("Error buscando presupuesto {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public Results<PresupuestoDTO> findByCriteria(
            PresupuestoCriteria criteria,
            int from,
            int pageSize) throws Exception {

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Results<PresupuestoDTO> results =
                    presupuestoDAO.findByCriteria(c, criteria, from, pageSize);

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
    public Long create(PresupuestoDTO dto) throws Exception {

        if (!validar(dto)) {
            return null;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Presupuesto p = new Presupuesto();

            p.setFecha(dto.getFecha());
            p.setPrecioFinal(dto.getPrecioFinal());
            p.setClienteId(dto.getClienteId());
            p.setEstadoPresupuestoId(dto.getEstadoPresupuestoId());
            p.setNombre(dto.getNombre());

            Long id = presupuestoDAO.create(c, p);

            commit = true;

            return id;

        } catch (Exception e) {

            logger.error("Creando presupuesto {}: {}", dto, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(PresupuestoDTO dto) throws Exception {

        if (dto == null || dto.getId() == null || dto.getId() <= 0) {
            return false;
        }

        if (!validar(dto)) {
            return false;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Presupuesto p = new Presupuesto();

            p.setId(dto.getId());
            p.setFecha(dto.getFecha());
            p.setPrecioFinal(dto.getPrecioFinal());
            p.setClienteId(dto.getClienteId());
            p.setEstadoPresupuestoId(dto.getEstadoPresupuestoId());
            p.setNombre(dto.getNombre());

            boolean updated = presupuestoDAO.update(c, p);

            commit = true;

            return updated;

        } catch (Exception e) {

            logger.error("Actualizando presupuesto {}: {}", dto, e.getMessage(), e);

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

            boolean deleted = presupuestoDAO.delete(c, id);

            commit = true;

            return deleted;

        } catch (Exception e) {

            logger.error("Error eliminando presupuesto {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    private boolean validar(PresupuestoDTO p) {

        if (p == null) {
            return false;
        }

        if (p.getFecha() == null) {
            return false;
        }

        if (p.getFecha().isAfter(LocalDate.now())) {
            return false;
        }

        if (p.getClienteId() == null) {
            return false;
        }

        if (p.getEstadoPresupuestoId() == null) {
            return false;
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            return false;
        }

        if (p.getPrecioFinal() == null || p.getPrecioFinal() < 0) {
            return false;
        }

        return true;
    }
}