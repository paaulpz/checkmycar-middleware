package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {

    private ProvinciaService service = null;

    public ProvinciaServiceTest() {
        this.service = new ProvinciaServiceImpl();
    }

   
    public void testFindById(Long id) {


        Provincia p = service.findById(id);

        if (p != null) {
            System.out.println("Provincia encontrada: " + p.getNombre());
        } else {
            System.out.println("No se encontró la provincia");
        }
    }

  
    public void testFindAll() {


        List<Provincia> provincias = service.findAll();

        System.out.println("Provincias encontradas: " + provincias.size());
    }

  
    public void testFindByPais(Long paisId) {


        List<Provincia> provincias = service.findByPais(paisId);

        System.out.println("Provincias encontradas: " + provincias.size());
    }

    public static void main(String[] args) {

        ProvinciaServiceTest test = new ProvinciaServiceTest();

        test.testFindById(1L);

        //test.testFindAll();

        //test.testFindByPais(1L);
    }
}