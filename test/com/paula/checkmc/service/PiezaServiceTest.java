package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.service.impl.PiezaServiceImpl;

public class PiezaServiceTest {

    private PiezaService service = null;

    public PiezaServiceTest() {
        this.service = new PiezaServiceImpl();
    }

   
  

    public void testFindById(Long id) {


        PiezaDTO pieza = service.findById(id);

        if (pieza != null) {
            System.out.println("Pieza encontrada: " + pieza.getNombre());
        } else {
            System.out.println("No se encontró la pieza");
        }
    }

    
    public void testFindAll() {


        List<PiezaDTO> piezas = service.findAll();

        System.out.println("Piezas encontradas: " + piezas.size());
    }

    
    public void testFindByCriteria() {


        PiezaCriteria criteria = new PiezaCriteria();

        criteria.setNombre("Filtro");

        List<PiezaDTO> piezas = service.findByCriteria(criteria, 0, 10);

        System.out.println("Resultados encontrados: " + piezas.size());
    }

    
  

    public static void main(String[] args) {

        PiezaServiceTest test = new PiezaServiceTest();

      

        test.testFindById(1L);

        //test.testFindAll();

        //test.testFindByCriteria();

        //test.testDelete(1L);
    }
}