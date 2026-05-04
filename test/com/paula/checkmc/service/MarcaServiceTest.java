package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Marca;
import com.paula.checkmc.service.impl.MarcaServiceImpl;

public class MarcaServiceTest {

    private MarcaService service = null;

    public MarcaServiceTest() {
        this.service = new MarcaServiceImpl();
    }

    
    public void testFindById(Long id) {


        Marca marca = service.findById(id);

        if (marca != null) {
            System.out.println("Marca encontrada: " + marca.getNombre());
        } else {
            System.out.println("No se encontró la marca");
        }
    }

  
    public void testFindAll() {


        List<Marca> marcas = service.findAll();

        System.out.println("Marcas encontradas: " + marcas.size());
    }

    public static void main(String[] args) {

        MarcaServiceTest test = new MarcaServiceTest();

        
        test.testFindById(1L);

       
        //test.testFindAll();
    }
}