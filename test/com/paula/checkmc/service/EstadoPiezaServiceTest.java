package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.service.EstadoPiezaService;
import com.paula.checkmc.service.impl.EstadoPiezaServiceImpl;

public class EstadoPiezaServiceTest {

    private EstadoPiezaService service = null;

    public EstadoPiezaServiceTest() {
        this.service = new EstadoPiezaServiceImpl();
    }

    
    public void testFindById(Long id) {

     
        EstadoPieza estado = service.findById(id);

        if (estado != null) {
            System.out.println("Estado encontrado: " + estado.getNombre());
        } else {
            System.out.println("No se encontró el estado de pieza");
        }
    }

  
    public void testFindAll() {

    
        List<EstadoPieza> estados = service.findAll();

        System.out.println("Estados encontrados: " + estados.size());
    }

    public static void main(String[] args) {

        EstadoPiezaServiceTest test = new EstadoPiezaServiceTest();

        test.testFindById(1L);

    
        //test.testFindAll();
    }
}