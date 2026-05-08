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

}