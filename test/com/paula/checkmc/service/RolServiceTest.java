package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.impl.RolServiceImpl;

public class RolServiceTest {

    private RolService service = null;

    public RolServiceTest() {
        this.service = new RolServiceImpl();
    }

    public void testFindById(Long id) {
        Rol rol = service.findById(id);
    }

    public void testFindAll() {
        List<Rol> roles = service.findAll();
    }

    public static void main(String[] args) {

        RolServiceTest test = new RolServiceTest();

        test.testFindById(1L);
//        test.testFindAll();
    }
}