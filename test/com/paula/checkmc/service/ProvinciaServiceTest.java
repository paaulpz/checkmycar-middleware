package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {

    private static final Logger logger =
            LogManager.getLogger(ProvinciaServiceTest.class);

    private ProvinciaService service = null;

    public ProvinciaServiceTest() {

        this.service = new ProvinciaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Provincia p = service.findById(id);

        if (p != null) {

            logger.info("Id: {}", p.getId());
            logger.info("Nombre: {}", p.getNombre());

        } else {

            logger.warn("No se encontró la provincia");
        }
    }

    public void testFindAll() throws Exception {

        List<Provincia> provincias = service.findAll();

        logger.info("Total provincias encontradas: {}",
                provincias.size());

        for (Provincia p : provincias) {

            logger.info("{} - {}", p.getId(), p.getNombre());
        }
    }

    public void testFindByPais(Long paisId) throws Exception {

        List<Provincia> provincias =
                service.findByPais(paisId);

        logger.info("Total provincias encontradas: {}",
                provincias.size());

        for (Provincia p : provincias) {

            logger.info("{} - {}", p.getId(), p.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        ProvinciaServiceTest test =
                new ProvinciaServiceTest();

        test.testFindById(1L);

        // test.testFindAll();

        // test.testFindByPais(1L);
    }
}