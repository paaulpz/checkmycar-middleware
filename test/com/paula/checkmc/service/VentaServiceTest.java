package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.service.impl.VentaServiceImpl;

public class VentaServiceTest {

    private VentaService service = null;

    public VentaServiceTest() {
        this.service = new VentaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {
        VentaDTO venta = service.findById(id);
    }

    public void testFindByCriteria() throws Exception {
        VentaCriteria criteria = new VentaCriteria();
        List<VentaDTO> ventas = service.findByCriteria(criteria, 0, 10);
    }

    public void testCreate() throws Exception {
        VentaDTO venta = new VentaDTO();
        Long id = service.create(venta);
    }

    public void testUpdate() throws Exception {
        VentaDTO venta = new VentaDTO();
        boolean updated = service.update(venta);
    }

    public void testDelete(Long id) throws Exception {
        boolean deleted = service.delete(id);
    }

    public static void main(String[] args) throws Exception {

        VentaServiceTest test = new VentaServiceTest();

        test.testFindById(1L);
        //test.testFindByCriteria();
        //test.testCreate();
        //test.testUpdate();
        //test.testDelete(1L);
    }
}