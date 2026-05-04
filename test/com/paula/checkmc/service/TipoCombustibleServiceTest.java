package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.service.impl.TipoCombustibleServiceImpl;

public class TipoCombustibleServiceTest {

    private TipoCombustibleService service = null;

    public TipoCombustibleServiceTest() {
        this.service = new TipoCombustibleServiceImpl();
    }

    public void testFindById(Long id) {
        TipoCombustible tipo = service.findById(id);
    }

    public void testFindAll() {
        List<TipoCombustible> tipos = service.findAll();
    }

    public static void main(String[] args) {

        TipoCombustibleServiceTest test = new TipoCombustibleServiceTest();

        test.testFindById(1L);
//        test.testFindAll();
    }
}