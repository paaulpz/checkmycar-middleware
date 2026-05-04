package com.paula.checkmc.service;

import com.paula.checkmc.service.impl.CocheServiceImpl;

public class CocheServiceTest {

    private CocheService service = null;

    public CocheServiceTest() {
        this.service = new CocheServiceImpl();
    }

  /*
    public void testCreate() {

        System.out.println("--- Test: CocheService.create ---");

        Coche coche = new Coche();

        coche.setDiagnostico("Revisión general");
        coche.setMatricula("1234ABC");
        coche.setNumeroBastidor("VF1ABC12345678901");
        coche.setAno(2018);
        coche.setPotenciaCv(120);
        coche.setKilometros(85000);

        coche.setClienteId(1L);
        coche.setModeloId(1L);
        coche.setTipoCombustibleId(1L);
        coche.setTipoTransmisionId(1L);
        coche.setTipoMotorId(1L);

        Long id = service.create(coche);

        if (id != null) {
            System.out.println("Coche creado con ID: " + id);
        } else {
            System.out.println("Error al crear coche");
        }
    }

 
   
    
    public void testFindByCriteria() {

        System.out.println("\n--- Test: CocheService.findByCriteria ---");

        CocheCriteria criteria = new CocheCriteria();

        criteria.setMatricula("1234ABC");

        List<Coche> resultados = service.findByCriteria(criteria, 0, 10);

        System.out.println("Resultados encontrados: " + resultados.size());
    }

   
    public void testDelete(Long id) {

        System.out.println("\n--- Test: CocheService.delete ---");

        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Coche eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el coche");
        }
    }
    */

    public static void main(String[] args) {

        CocheServiceTest test = new CocheServiceTest();

        
         //test.testCreate();
         //test.testFindByCriteria();
        //test.testDelete(1L);
    }
}