package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.service.LocalidadService;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {

    private LocalidadService service = null;

    public LocalidadServiceTest() {
        this.service = new LocalidadServiceImpl();
    }

    public void testFindById(Long id) {


        Localidad l = service.findById(id);

        if (l != null) {
            System.out.println("Localidad encontrada: " + l.getNombre());
        } else {
            System.out.println("No se encontró la localidad");
        }
    }

  
    public void testFindAll() {


        List<Localidad> localidades = service.findAll();

        System.out.println("Localidades encontradas: " + localidades.size());
    }

   
    public void testFindByProvincia(Long provinciaId) {

       

        List<Localidad> localidades = service.findByProvincia(provinciaId);

        System.out.println("Localidades encontradas: " + localidades.size());
    }

    
    public void testFindByNombre(String nombre) {

        

        List<Localidad> localidades = service.findByNombre(nombre);

        System.out.println("Localidades encontradas: " + localidades.size());
    }

    public static void main(String[] args) {

        LocalidadServiceTest test = new LocalidadServiceTest();

       
        test.testFindById(1L);

     
        //test.testFindAll();

     
         //test.testFindByProvincia(1L);

        //test.testFindByNombre("Lugo");
    }
}