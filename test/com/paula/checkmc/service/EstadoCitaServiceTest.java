package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.service.impl.EstadoCitaServiceImpl;

public class EstadoCitaServiceTest {

    private EstadoCitaService service = null;

    public EstadoCitaServiceTest() {
        this.service = new EstadoCitaServiceImpl();
    }

    
    public void testFindById(Long id) {

        

        EstadoCita estado = service.findById(id);

        if (estado != null) {
            System.out.println("Estado encontrado: " + estado.getNombre());
        } else {
            System.out.println("No se encontró el estado");
        }
    }

   
    public void testFindAll() {

      

        List<EstadoCita> estados = service.findAll();

        System.out.println("Estados encontrados: " + estados.size());
    }

    public static void main(String[] args) {

        EstadoCitaServiceTest test = new EstadoCitaServiceTest();

      
        test.testFindById(1L);

      
       //test.testFindAll();
    }
}