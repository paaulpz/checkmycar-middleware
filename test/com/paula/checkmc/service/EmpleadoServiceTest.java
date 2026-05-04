package com.paula.checkmc.service;

import com.paula.checkmc.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {

    private EmpleadoService service = null;

    public EmpleadoServiceTest() {
        this.service = new EmpleadoServiceImpl();
    }

  /*  
    public void testCreate() {

        System.out.println("--- Test: EmpleadoService.create ---");

        Empleado e = new Empleado();

        e.setNombre("Carlos");
        e.setPrimerApellido("Perez");
        e.setSegundoApellido("Garcia");
        e.setDniNie("12345678A");
        e.setEmail("carlos@test.com");
        e.setTelefono("666777888");
        e.setFechaContratacion(LocalDate.now());
     

        e.setRolId(1L);
        e.setGeneroId(1L);
        e.setLocalidadId(1L);

        Long id = service.create(e);

        if (id != null) {
            System.out.println("Empleado creado con ID: " + id);
        } else {
            System.out.println("Error al crear empleado");
        }
    }

    
  
   
    public void testFindByCriteria() {

        System.out.println("\n--- Test: EmpleadoService.findByCriteria ---");

        EmpleadoCriteria criteria = new EmpleadoCriteria();

        criteria.setNombre("Carlos");

        List<Empleado> resultados = service.findByCriteria(criteria, 0, 10);

        System.out.println("Resultados encontrados: " + resultados.size());
    }

    
    public void testDelete(Long id) {

        System.out.println("\n--- Test: EmpleadoService.delete ---");

        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Empleado eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el empleado");
        }
    }
*/
    public static void main(String[] args) {

        EmpleadoServiceTest test = new EmpleadoServiceTest();

       
       // test.testCreate();

        

       
       //test.testFindByCriteria();


      //test.testDelete(1L);
    }
}