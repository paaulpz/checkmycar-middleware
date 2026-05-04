package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Genero;
import com.paula.checkmc.service.impl.GeneroServiceImpl;

public class GeneroServiceTest {

    private GeneroService service = null;

    public GeneroServiceTest() {
        this.service = new GeneroServiceImpl();
    }

    
    public void testFindById(Long id) {


        Genero genero = service.findById(id);

        if (genero != null) {
            System.out.println("Genero encontrado: " + genero.getNombre());
        } else {
            System.out.println("No se encontró el genero");
        }
    }

    
    public void testFindAll() {

      

        List<Genero> generos = service.findAll();

        System.out.println("Generos encontrados: " + generos.size());
    }

    public static void main(String[] args) {

        GeneroServiceTest test = new GeneroServiceTest();

       
        test.testFindById(1L);

        //test.testFindAll();
    }
}