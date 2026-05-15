package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.service.impl.EstadoCitaServiceImpl;

public class EstadoCitaServiceTest {

    private static final Logger logger =
            LogManager.getLogger(EstadoCitaServiceTest.class);

    private EstadoCitaService service = null;

    public EstadoCitaServiceTest() {

        this.service = new EstadoCitaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        EstadoCita estado = service.findById(id);

        if (estado != null) {

            logger.info("Id: {}", estado.getId());
            logger.info("Nombre: {}", estado.getNombre());

        } else {

            logger.warn("No se encontró el estado");
        }
    }

    public void testFindAll() throws Exception {

        List<EstadoCita> estados = service.findAll();

        logger.info("Total estados encontrados: {}", estados.size());

        for (EstadoCita e : estados) {

            logger.info("{} - {}", e.getId(), e.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        EstadoCitaServiceTest test = new EstadoCitaServiceTest();

        test.testFindById(1L);

        test.testFindAll();
    }
}