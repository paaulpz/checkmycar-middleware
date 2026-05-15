package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Marca;
import com.paula.checkmc.service.impl.MarcaServiceImpl;

public class MarcaServiceTest {

    private static final Logger logger =
            LogManager.getLogger(MarcaServiceTest.class);

    private MarcaService service = null;

    public MarcaServiceTest() {

        this.service = new MarcaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Marca marca = service.findById(id);

        if (marca != null) {

            logger.info("Id: {}", marca.getId());
            logger.info("Nombre: {}", marca.getNombre());

        } else {

            logger.warn("No se encontró la marca");
        }
    }

    public void testFindAll() throws Exception {

        List<Marca> marcas = service.findAll();

        logger.info("Total marcas encontradas: {}", marcas.size());

        for (Marca m : marcas) {

            logger.info("{} - {}", m.getId(), m.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        MarcaServiceTest test = new MarcaServiceTest();

        test.testFindById(1L);

        // test.testFindAll();
    }
}