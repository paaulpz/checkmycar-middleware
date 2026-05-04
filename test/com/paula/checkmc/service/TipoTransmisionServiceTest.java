package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.impl.TipoTransmisionServiceImpl;

public class TipoTransmisionServiceTest {

    private TipoTransmisionService service = null;

    public TipoTransmisionServiceTest() {
        this.service = new TipoTransmisionServiceImpl();
    }

    public void testFindById(Long id) {
        TipoTransmision tipo = service.findById(id);
    }

    public void testFindAll() {
        List<TipoTransmision> tipos = service.findAll();
    }

    public static void main(String[] args) {

        TipoTransmisionServiceTest test = new TipoTransmisionServiceTest();

        test.testFindById(1L);
        //test.testFindAll();
    }
}