package com.paula.checkmc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.dao.LineaPresupuestoDAO;
import com.paula.checkmc.model.LineaPresupuesto;
import com.paula.checkmc.model.LineaPresupuestoDTO;
import com.paula.checkmc.service.LineaPresupuestoService;

public class LineaPresupuestoServiceImpl implements LineaPresupuestoService {

    private LineaPresupuestoDAO dao = new LineaPresupuestoDAO();

    @Override
    public List<LineaPresupuestoDTO> findByPresupuesto(Long presupuestoId) throws Exception {

        List<LineaPresupuesto> lineas = dao.findByPresupuesto(presupuestoId);
        List<LineaPresupuestoDTO> res = new ArrayList<>();

        for (LineaPresupuesto l : lineas) {

            LineaPresupuestoDTO dto = new LineaPresupuestoDTO();

            dto.setId(l.getId());
            dto.setUnidades(l.getUnidades());
            dto.setPrecioUnitario(l.getPrecioUnitario());
            dto.setPrecioFinal(l.getPrecioFinal());
            dto.setPiezaId(l.getPiezaId());
            dto.setPresupuestoId(l.getPresupuestoId());

            res.add(dto);
        }

        return res;
    }

    @Override
    public Long create(LineaPresupuestoDTO dto) throws Exception {

        if (!validar(dto)) return null;

        // cálculo precio final
        dto.setPrecioFinal(dto.getUnidades() * dto.getPrecioUnitario());

        LineaPresupuesto l = new LineaPresupuesto();

        l.setUnidades(dto.getUnidades());
        l.setPrecioUnitario(dto.getPrecioUnitario());
        l.setPrecioFinal(dto.getPrecioFinal());
        l.setPiezaId(dto.getPiezaId());
        l.setPresupuestoId(dto.getPresupuestoId());

        return dao.create(l);
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return dao.delete(id);
    }

    private boolean validar(LineaPresupuestoDTO l) {

        if (l == null) return false;

        if (l.getUnidades() == null || l.getUnidades() <= 0) return false;

        if (l.getPrecioUnitario() == null || l.getPrecioUnitario() < 0) return false;

        if (l.getPiezaId() == null) return false;

        if (l.getPresupuestoId() == null) return false;

        return true;
    }
}