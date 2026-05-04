package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.service.TipoMotorService;
import com.paula.checkmc.service.impl.TipoMotorServiceImpl;

public class TipoMotorServiceTest {

    private TipoMotorService service = null;

    public TipoMotorServiceTest() {
        this.service = new TipoMotorServiceImpl();
    }

    public void testFindById(Long id) {
        TipoMotor tipo = service.findById(id);
    }

    public void testFindAll() {
        List<TipoMotor> tipos = service.findAll();
    }

    public static void main(String[] args) {

        TipoMotorServiceTest test = new TipoMotorServiceTest();

        test.testFindById(1L);
        //test.testFindAll();
    }
}