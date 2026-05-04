package com.paula.checkmc.service;

import java.math.BigDecimal;
import java.util.List;

import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.service.PiezaService;
import com.paula.checkmc.service.impl.PiezaServiceImpl;

public class PiezaServiceTest {

    private PiezaService service = null;

    public PiezaServiceTest() {
        this.service = new PiezaServiceImpl();
    }

   
    public void testCreate() {


        PiezaDTO pieza = new PiezaDTO();

        pieza.setNombre("Filtro de aceite");
        pieza.setStock(10);
        pieza.setEstadoId(1L);
        pieza.setPrecio(new BigDecimal("15.50"));

        Long id = service.create(pieza);

        if (id != null) {
            System.out.println("Pieza creada con ID: " + id);
        } else {
            System.out.println("Error al crear la pieza");
        }
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

    
    public void testDelete(Long id) {


        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Pieza eliminada correctamente");
        } else {
            System.out.println("No se pudo eliminar la pieza");
        }
    }

    public static void main(String[] args) {

        PiezaServiceTest test = new PiezaServiceTest();

        test.testCreate();

        //test.testFindById(1L);

        //test.testFindAll();

        //test.testFindByCriteria();

        //test.testDelete(1L);
    }
}