package com.paula.checkmc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.ClienteServiceImpl;

public class ClienteServiceTest {

    private static final Logger logger =
            LogManager.getLogger(ClienteServiceTest.class);

    private ClienteService service = null;

    private Long createdClienteId;

    public ClienteServiceTest() {

        this.service = new ClienteServiceImpl();
    }

    public void testCreate() throws Exception {

        Cliente c = new Cliente();

        c.setNombre("Maria");
        c.setPrimerApellido("Gomez");
        c.setSegundoApellido("Lopez");
        c.setDniNie("097213468S");
        c.setEmail("pu@test.com");
        c.setTelefono("669777488");

        c.setLocalidadId(1L);
        c.setGeneroId(1L);

        Long id = service.create(c);

        if (id != null) {

            createdClienteId = id;

            logger.info("Cliente creado correctamente con ID: {}", id);

        } else {

            logger.warn("Error al crear cliente");
        }
    }

    public void testFindByCriteria() throws Exception {

        ClienteCriteria criteria = new ClienteCriteria();

        criteria.setDniNie("097213468S");

        Results<ClienteDTO> results =
                service.findByCriteria(criteria, 1, 10);

        print(results);
    }

    public void testDelete(Long id) throws Exception {

        boolean eliminado = service.delete(id);

        if (eliminado) {

            logger.info("Cliente eliminado correctamente");

        } else {

            logger.warn("No se pudo eliminar el cliente");
        }
    }

    public void testPageFindBy() throws Exception {

        ClienteCriteria criteria = new ClienteCriteria();

        int pageSize = 5;

        Results<ClienteDTO> results = null;

        int from = 1;

        do {

            results = service.findByCriteria(criteria, from, pageSize);

            print(results);

            from = from + pageSize;

        } while (from <= results.getTotal());
    }

    private void print(Results<ClienteDTO> results) {

        logger.info("Imprimiendo página...");
        logger.info("Total resultados: {}", results.getTotal());

        for (ClienteDTO c : results.getPage()) {

            logger.info("{}", c);
        }
    }

    public static void main(String[] args) throws Exception {

        ClienteServiceTest test = new ClienteServiceTest();

        logger.info("TESTS CLIENTE SERVICE");

        // test.testCreate();

        test.testPageFindBy();

        // test.testFindByCriteria();

        // test.testDelete(1L);
    }
}