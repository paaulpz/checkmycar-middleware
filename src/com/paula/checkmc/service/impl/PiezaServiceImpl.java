package com.paula.checkmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.dao.PiezaDAO;
import com.paula.checkmc.model.Pieza;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.service.PiezaService;

public class PiezaServiceImpl implements PiezaService {

    private PiezaDAO dao = new PiezaDAO();

    @Override
    public PiezaDTO findById(Long id) {

        if (id == null || id <= 0) return null;

        Pieza p = dao.findById(id);

        if (p == null) return null;

        PiezaDTO dto = new PiezaDTO();

        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setStock(p.getStock());
        dto.setEstadoId(p.getEstadoId());
        dto.setPrecio(p.getPrecio());

        return dto;
    }

    @Override
    public List<PiezaDTO> findAll() {

        List<Pieza> piezas = dao.findAll();
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
    }

    @Override
    public List<PiezaDTO> findByCriteria(PiezaCriteria criteria, int from , int pageSize) {

        if (criteria == null) return null;

        List<Pieza> piezas = dao.findByCriteria(criteria);
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
    }

    @Override
    public Long create(PiezaDTO dto) {

        if (dto == null) return null;

        Pieza p = new Pieza();

        p.setNombre(dto.getNombre());
        p.setStock(dto.getStock());
        p.setEstadoId(dto.getEstadoId());
        p.setPrecio(dto.getPrecio());

        return dao.create(p);
    }

    @Override
    public boolean update(PiezaDTO dto) {

        if (dto == null || dto.getId() == null) return false;

        Pieza p = new Pieza();

        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setStock(dto.getStock());
        p.setEstadoId(dto.getEstadoId());
        p.setPrecio(dto.getPrecio());

        return dao.update(p);
    }

    @Override
    public boolean delete(Long id) {

        if (id == null || id <= 0) return false;

        return dao.delete(id);
    }
}