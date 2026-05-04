package com.paula.checkmc.service;

import com.paula.checkmc.service.impl.PresupuestoServiceImpl;

public class PresupuestoServiceTest {

    private PresupuestoService service = null;

    public PresupuestoServiceTest() {
        this.service = new PresupuestoServiceImpl();
    }

   /* public void testCreate() throws Exception {


        PresupuestoDTO p = new PresupuestoDTO();

        p.setFecha(LocalDate.now());
        p.setPrecioFinal(150.0);
        p.setClienteId(1L);
        p.setEstadoPresupuestoId(1L);
        p.setNombre("Presupuesto prueba");

        Long id = service.create(p);

        if (id != null) {
            System.out.println("Presupuesto creado con ID: " + id);
        } else {
            System.out.println("Error al crear el presupuesto");
        }
    }

   
    public void testFindById(Long id) throws Exception {


        PresupuestoDTO p = service.findById(id);

        if (p != null) {
            System.out.println("Presupuesto encontrado: " + p.getNombre());
        } else {
            System.out.println("No se encontró el presupuesto");
        }
    }

   
    public void testFindByCriteria() throws Exception {


        PresupuestoCriteria criteria = new PresupuestoCriteria();

        criteria.setClienteId(1L);

        List<PresupuestoDTO> resultados = service.findByCriteria(criteria, 0, 10);

        System.out.println("Presupuestos encontrados: " + resultados.size());
    }

   
    public void testDelete(Long id) throws Exception {


        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Presupuesto eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el presupuesto");
        }
    }
*/
    public static void main(String[] args) throws Exception {

        PresupuestoServiceTest test = new PresupuestoServiceTest();

      //  test.testCreate();

        //test.testFindById(1L);

        //test.testFindByCriteria();

        //test.testDelete(1L);
    }
}