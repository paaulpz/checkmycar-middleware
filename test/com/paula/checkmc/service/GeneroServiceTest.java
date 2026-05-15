package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Genero;
import com.paula.checkmc.service.impl.GeneroServiceImpl;

public class GeneroServiceTest {

    private static final Logger logger = LogManager.getLogger(GeneroServiceTest.class);

    private GeneroService service;

    public GeneroServiceTest() {

        service = new GeneroServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Genero genero = service.findById(id);

        if (genero != null) {

            logger.info("Id: {}", genero.getId());
            logger.info("Nombre: {}", genero.getNombre());

        } else {

            logger.warn("No existe el genero");
        }
    }

    public void testFindAll() throws Exception {

        List<Genero> lista = service.findAll();

        logger.info("Total registros: {}", lista.size());

        for (Genero g : lista) {

            logger.info("{} - {}", g.getId(), g.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        GeneroServiceTest test = new GeneroServiceTest();

        test.testFindById(1L);

        // test.testFindAll();
    }
}