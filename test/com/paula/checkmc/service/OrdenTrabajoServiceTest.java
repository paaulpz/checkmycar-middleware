package com.paula.checkmc.service;

import com.paula.checkmc.service.impl.OrdenTrabajoServiceImpl;

public class OrdenTrabajoServiceTest {

    private OrdenTrabajoService service = null;

    public OrdenTrabajoServiceTest() {
        this.service = new OrdenTrabajoServiceImpl();
    }
/*
    public void testCreate() throws Exception {

        OrdenTrabajoDTO orden = new OrdenTrabajoDTO();

        orden.setDiagnostico("Cambio de aceite");
        orden.setFechaInicio(LocalDate.now());
        orden.setFechaFin(LocalDate.now().plusDays(1));

        orden.setPresupuestoId(1L);
        orden.setCocheId(1L);

        Long id = service.create(orden);

        if (id != null) {
            System.out.println("Orden creada con ID: " + id);
        } else {
            System.out.println("Error al crear orden");
        }
    }

   
    public void testFindById(Long id) throws Exception {


        OrdenTrabajoDTO orden = service.findById(id);

        if (orden != null) {
            System.out.println("Orden encontrada: " + orden.getDiagnostico());
        } else {
            System.out.println("No se encontró la orden");
        }
    }

   
    public void testFindByCriteria() throws Exception {


        OrdenTrabajoCriteria criteria = new OrdenTrabajoCriteria();

        criteria.setCocheId(1L);

        List<OrdenTrabajoDTO> resultados = service.findByCriteria(criteria, 0, 10);

        System.out.println("Órdenes encontradas: " + resultados.size());
    }

   
    public void testDelete(Long id) throws Exception {


        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Orden eliminada correctamente");
        } else {
            System.out.println("No se pudo eliminar la orden");
        }
    }
    
    */

    public static void main(String[] args) throws Exception {

        OrdenTrabajoServiceTest test = new OrdenTrabajoServiceTest();

       
      //  test.testCreate();

     
        //test.testFindById(1L);

      
        //test.testFindByCriteria();

    
        //test.testDelete(1L);
    }
}