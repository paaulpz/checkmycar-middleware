package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Pais;
import com.paula.checkmc.service.impl.PaisServiceImpl;

public class PaisServiceTest {

    private static final Logger logger =
            LogManager.getLogger(PaisServiceTest.class);

    private PaisService service = null;

    public PaisServiceTest() {

        this.service = new PaisServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Pais pais = service.findById(id);

        if (pais != null) {

            logger.info("Id: {}", pais.getId());
            logger.info("Nombre: {}", pais.getNombre());

        } else {

            logger.warn("No se encontró el pais");
        }
    }

    public void testFindAll() throws Exception {

        List<Pais> paises = service.findAll();

        logger.info("Total paises encontrados: {}", paises.size());

        for (Pais p : paises) {

            logger.info("{} - {}", p.getId(), p.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        PaisServiceTest test = new PaisServiceTest();

        test.testFindById(1L);

        // test.testFindAll();
    }
}