package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {

    private static final Logger logger = LogManager.getLogger(LocalidadServiceTest.class);

    private LocalidadService service;

    public LocalidadServiceTest() {

        service = new LocalidadServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Localidad localidad = service.findById(id);

        if (localidad != null) {

            logger.info("Id: {}", localidad.getId());
            logger.info("Nombre: {}", localidad.getNombre());

        } else {

            logger.warn("No existe la localidad");
        }
    }

    public void testFindAll() throws Exception {

        List<Localidad> lista = service.findAll();

        logger.info("Total localidades: {}", lista.size());

        for (Localidad l : lista) {

            logger.info("{} - {}", l.getId(), l.getNombre());
        }
    }

    public void testFindByProvincia(Long provinciaId) throws Exception {

        List<Localidad> lista = service.findByProvincia(provinciaId);

        logger.info("Resultados encontrados: {}", lista.size());

        for (Localidad l : lista) {

            logger.info("{} - {}", l.getId(), l.getNombre());
        }
    }

    public void testFindByNombre(String nombre) throws Exception {

        List<Localidad> lista = service.findByNombre(nombre);

        logger.info("Resultados encontrados: {}", lista.size());

        for (Localidad l : lista) {

            logger.info("{} - {}", l.getId(), l.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        LocalidadServiceTest test = new LocalidadServiceTest();

        test.testFindById(1L);

        // test.testFindAll();

        // test.testFindByProvincia(1L);

        // test.testFindByNombre("Lugo");
    }
}