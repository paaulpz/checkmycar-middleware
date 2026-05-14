package com.paula.checkmc.service;

import java.time.LocalDateTime;

import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.CitaServiceImpl;

public class CitaServiceTest {

    private CitaService service = null;

    public CitaServiceTest() {
        this.service = new CitaServiceImpl();
    }

  
    public void testCreate() throws Exception {

        System.out.println("--- Test: CitaService.create ---");

        CitaDTO cita = new CitaDTO();

        cita.setDescripcion("Cambio de aceite");
        cita.setFecha(LocalDateTime.now());
        cita.setClienteId(1L);
        cita.setCocheId(1L);
        cita.setEstadoCitaId(1L);

        Long id = service.create(null); 

        if (id != null) {
            System.out.println("Cita creada con ID: " + id);
        } else {
            System.out.println("Error al crear la cita");
        }
    }

    /**
     * Test buscar citas
     */
    /*
    public void testFindByCriteria() throws Exception {

        System.out.println("\n--- Test: CitaService.findByCriteria ---");

        CitaCriteria criteria = new CitaCriteria();

        criteria.setClienteId(1L);

        List<CitaDTO> resultados = service.findByCriteria(criteria, 1 , 10); 

        System.out.println("Citas encontradas: " + resultados.size());
    }

   
    public void testDelete(Long id) throws Exception {

        System.out.println("\n--- Test: CitaService.delete ---");

        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Cita eliminada correctamente");
        } else {
            System.out.println("No se pudo eliminar la cita");
        }
    }
    
    */
    
    public void testPageFindBy() throws Exception {
    	CitaCriteria criteria = new CitaCriteria(); 
    	int pageSize = 5 ; 
    	Results<CitaDTO> results = null; 
    	int from = 1 ; 
    	
    	do {
    		results = service.findByCriteria(criteria, from, pageSize); 
    		print(results);
    		
    		from = from + pageSize; 
    	} while (from <results.getTotal());
    }
    
    private void print(Results<CitaDTO> results) {
    	System.out.println("Imprimiendo página... .................");
    	System.out.println("Total resultados: "+results.getTotal());
		for (CitaDTO c : results.getPage()) {
			System.out.println(c);
			
		}
    }

    public static void main(String[] args) throws Exception {

        CitaServiceTest test = new CitaServiceTest();

         //  test.testPageFindBy();
         test.testCreate();
        //test.testFindByCriteria();
        //test.testDelete(1L);
    }
}