package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.service.impl.EstadoPiezaServiceImpl;

public class EstadoPiezaServiceTest {

    private static final Logger logger =
            LogManager.getLogger(EstadoPiezaServiceTest.class);

    private EstadoPiezaService service = null;

    public EstadoPiezaServiceTest() {

        this.service = new EstadoPiezaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        EstadoPieza estado = service.findById(id);

        if (estado != null) {

            logger.info("Id: {}", estado.getId());
            logger.info("Nombre: {}", estado.getNombre());

        } else {

            logger.warn("No se encontró el estado de pieza");
        }
    }

    public void testFindAll() throws Exception {

        List<EstadoPieza> estados = service.findAll();

        logger.info("Total estados encontrados: {}", estados.size());

        for (EstadoPieza e : estados) {

            logger.info("{} - {}", e.getId(), e.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        EstadoPiezaServiceTest test = new EstadoPiezaServiceTest();

        test.testFindById(1L);

        test.testFindAll();
    }
}