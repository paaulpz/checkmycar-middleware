package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoCombustible;
import com.paula.checkmc.service.impl.TipoCombustibleServiceImpl;

public class TipoCombustibleServiceTest {

    private static final Logger logger =
            LogManager.getLogger(TipoCombustibleServiceTest.class);

    private TipoCombustibleService service = null;

    public TipoCombustibleServiceTest() {

        this.service = new TipoCombustibleServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        TipoCombustible tipo = service.findById(id);

        if (tipo != null) {

            logger.info("Id: {}", tipo.getId());
            logger.info("Nombre: {}", tipo.getNombre());

        } else {

            logger.warn("No se encontró el tipo de combustible");
        }
    }

    public void testFindAll() throws Exception {

        List<TipoCombustible> tipos = service.findAll();

        logger.info("Total tipos encontrados: {}", tipos.size());

        for (TipoCombustible t : tipos) {

            logger.info("{} - {}", t.getId(), t.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        TipoCombustibleServiceTest test =
                new TipoCombustibleServiceTest();

        test.testFindById(1L);

        // test.testFindAll();
    }
}