package com.paula.checkmc.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.PiezaDAO;
import com.paula.checkmc.model.Pieza;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.service.PiezaService;
import com.paula.checkmc.util.JDBCUtils;

public class PiezaServiceImpl implements PiezaService {

    private static final Logger logger = LogManager.getLogger(PiezaServiceImpl.class);

    private PiezaDAO piezaDAO = new PiezaDAO();

    @Override
    public PiezaDTO findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            Pieza p = piezaDAO.findById(c, id);

            if (p == null) {
                return null;
            }

            PiezaDTO dto = new PiezaDTO();

            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setStock(p.getStock());
            dto.setEstadoId(p.getEstadoId());
            dto.setPrecio(p.getPrecio());

            return dto;

        } catch (Exception e) {

            logger.error("Error buscando pieza {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<PiezaDTO> findAll() throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            List<Pieza> piezas = piezaDAO.findAll(c);

            List<PiezaDTO> res = new ArrayList<>();

            for (Pieza p : piezas) {

                PiezaDTO dto = new PiezaDTO();

                dto.setId(p.getId());
                dto.setNombre(p.getNombre());
                dto.setStock(p.getStock());
                dto.setEstadoId(p.getEstadoId());
                dto.setPrecio(p.getPrecio());

                res.add(dto);
            }

            return res;

        } catch (Exception e) {

            logger.error("Error listando piezas: {}", e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public List<PiezaDTO> findByCriteria(PiezaCriteria criteria, int from, int pageSize)
            throws Exception {

        if (criteria == null) {
            return new ArrayList<>();
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            List<Pieza> piezas = piezaDAO.findByCriteria(c, criteria);

            List<PiezaDTO> res = new ArrayList<>();

            for (Pieza p : piezas) {

                PiezaDTO dto = new PiezaDTO();

                dto.setId(p.getId());
                dto.setNombre(p.getNombre());
                dto.setStock(p.getStock());
                dto.setEstadoId(p.getEstadoId());
                dto.setPrecio(p.getPrecio());

                res.add(dto);
            }

            return res;

        } catch (Exception e) {

            logger.error("Error buscando piezas {}: {}", criteria, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }
}