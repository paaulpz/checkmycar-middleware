package com.paula.checkmc.service;

import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.ClienteServiceImpl;

public class ClienteServiceTest {

    private ClienteService service = null;
    
    private Long createdClienteId ; 

    public ClienteServiceTest() {
        this.service = new ClienteServiceImpl();
    }

    /*
    
    public void testCreate() {

        

        Cliente c = new Cliente();

   
        c.setNombre("Maria");
        c.setPrimerApellido("Gomez");
        c.setDniNie("097213468S");
        c.setSegundoApellido("Lopez");
        c.setEmail("pu@test.com");
        c.setTelefono("669777488");

        c.setLocalidadId(1L);
        c.setGeneroId(1L);

        Long id = service.create(c);

        if (id != null) {
            System.out.println("Cliente creado con ID: " + id);
        } else {
            System.out.println("Error al crear cliente");
        }
    }

  
 
    public void testFindByCriteria() {

       

        ClienteCriteria criteria = new ClienteCriteria();

        criteria.setDniNie("12465768S");

        List<Cliente> resultados = service.findByCriteria(criteria, 1, 10);

      System.out.println("Resultados encontrados: " + resultados.size());
    }
    
       public void testDelete(Long id) {

        

        boolean eliminado = service.delete(id);

        if (eliminado) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el cliente");
        }
    }

  */
    public void testPageFindBy() {
        ClienteCriteria criteria = new ClienteCriteria();
        int pageSize = 5;
        Results<ClienteDTO> results = null;
        int from = 1;

        do {
        	results = service.findByCriteria(criteria, from, pageSize); 
            print(results);

            from = from + pageSize;

        } while (from< results.getTotal());
    }
    
    private void print(Results<ClienteDTO> results) {
    	System.out.println("Imprimiendo página... .................");
    	System.out.println("Total resultados: "+results.getTotal());
		for (ClienteDTO c : results.getPage()) {
			System.out.println(c);
		}
	}
    
    
 
    public static void main(String[] args) {

        ClienteServiceTest test = new ClienteServiceTest();
        System.out.println("TESTS CLIENTE SERVICE");
        
       //test.testCreate();.
        test.testPageFindBy();
        //test.testFindByCriteria();
        //test.testDelete(1L);
    }
}