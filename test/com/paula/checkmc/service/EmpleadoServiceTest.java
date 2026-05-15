package com.paula.checkmc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.EmpleadoServiceImpl;

public class EmpleadoServiceTest {

    private static final Logger logger =
            LogManager.getLogger(EmpleadoServiceTest.class);

    private EmpleadoService service = null;

    public EmpleadoServiceTest() {

        this.service = new EmpleadoServiceImpl();
    }

    public void testCreate() throws Exception {

        Empleado e = new Empleado();

        e.setNombre("Carlos");
        e.setPrimerApellido("Perez");
        e.setSegundoApellido("Garcia");
        e.setDniNie("12345678A");
        e.setEmail("carlos@test.com");
        e.setTelefono("666777888");
        e.setPassword("1234");

        e.setRolId(1L);
        e.setGeneroId(1L);
        e.setLocalidadId(1L);

        Long id = service.create(e);

        if (id != null) {

            logger.info("Empleado creado correctamente con ID: {}", id);

        } else {

            logger.warn("Error al crear empleado");
        }
    }

    public void testFindByCriteria() throws Exception {

        EmpleadoCriteria criteria = new EmpleadoCriteria();

        criteria.setNombre("Carlos");

        Results<EmpleadoDTO> results =
                service.findByCriteria(criteria, 1, 10);

        print(results);
    }

    public void testDelete(Long id) throws Exception {

        boolean eliminado = service.delete(id);

        if (eliminado) {

            logger.info("Empleado eliminado correctamente");

        } else {

            logger.warn("No se pudo eliminar el empleado");
        }
    }

    public void testPageFindBy() throws Exception {

        EmpleadoCriteria criteria = new EmpleadoCriteria();

        int pageSize = 5;

        Results<EmpleadoDTO> results = null;

        int from = 1;

        do {

            results = service.findByCriteria(criteria, from, pageSize);

            print(results);

            from = from + pageSize;

        } while (from <= results.getTotal());
    }

    private void print(Results<EmpleadoDTO> results) {

        logger.info("Imprimiendo página...");
        logger.info("Total resultados: {}", results.getTotal());

        for (EmpleadoDTO e : results.getPage()) {

            logger.info("{}", e);
        }
    }

    public static void main(String[] args) throws Exception {

        EmpleadoServiceTest test = new EmpleadoServiceTest();

        // test.testCreate();

        test.testPageFindBy();

        // test.testFindByCriteria();

        // test.testDelete(1L);
    }
}