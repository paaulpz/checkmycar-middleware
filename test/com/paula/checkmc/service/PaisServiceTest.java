package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Pais;
import com.paula.checkmc.service.PaisService;
import com.paula.checkmc.service.impl.PaisServiceImpl;

public class PaisServiceTest {

    private PaisService service = null;

    public PaisServiceTest() {
        this.service = new PaisServiceImpl();
    }

    
    public void testFindById(Long id) {


        Pais pais = service.findById(id);

        if (pais != null) {
            System.out.println("Pais encontrado: " + pais.getNombre());
        } else {
            System.out.println("No se encontró el pais");
        }
    }

   
    public void testFindAll() {


        List<Pais> paises = service.findAll();

        System.out.println("Paises encontrados: " + paises.size());
    }

    public static void main(String[] args) {

        PaisServiceTest test = new PaisServiceTest();

       
        test.testFindById(1L);

        //test.testFindAll();
    }
}