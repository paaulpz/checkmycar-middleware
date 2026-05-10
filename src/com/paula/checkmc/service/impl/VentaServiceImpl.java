package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.dao.VentaDAO;
import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;

public class VentaServiceImpl implements VentaService {

    private VentaDAO dao = new VentaDAO();

    @Override
    public VentaDTO findById(Long id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public List<VentaDTO> findByCriteria(VentaCriteria criteria, int from , int pageSize) throws Exception {
        return dao.findByCriteria(criteria, from, pageSize);
    }

    

    @Override
    public boolean delete(Long id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public Long create(VentaDTO venta) throws Exception {
        Venta v = new Venta();
        v.setFechaVenta(venta.getFechaVenta());
        v.setPrecioCliente(venta.getPrecioCliente());
        v.setPrecioFinal(venta.getPrecioFinal());
        v.setClienteCompradorId(venta.getClienteCompradorId());
        v.setClienteVendedorId(venta.getClienteVendedorId());
        v.setEmpleadoId(venta.getEmpleadoId());
        v.setCocheId(venta.getCocheId());
        return dao.create(v);
    }

    @Override
    public boolean update(VentaDTO venta) throws Exception {
        Venta v = new Venta();
        v.setId(venta.getId());
        v.setFechaVenta(venta.getFechaVenta());
        v.setPrecioCliente(venta.getPrecioCliente());
        v.setPrecioFinal(venta.getPrecioFinal());
        v.setClienteCompradorId(venta.getClienteCompradorId());
        v.setClienteVendedorId(venta.getClienteVendedorId());
        v.setEmpleadoId(venta.getEmpleadoId());
        v.setCocheId(venta.getCocheId());
        return dao.update(v);
    }
}