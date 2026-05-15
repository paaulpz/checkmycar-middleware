package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.service.impl.EstadoPresupuestoServiceImpl;

public class EstadoPresupuestoServiceTest {

    private static final Logger logger =
            LogManager.getLogger(EstadoPresupuestoServiceTest.class);

    private EstadoPresupuestoService service = null;

    public EstadoPresupuestoServiceTest() {

        this.service = new EstadoPresupuestoServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        EstadoPresupuesto estado = service.findById(id);

        if (estado != null) {

            logger.info("Id: {}", estado.getId());
            logger.info("Nombre: {}", estado.getNombre());

        } else {

            logger.warn("No se encontró el estado presupuesto");
        }
    }

    public void testFindAll() throws Exception {

        List<EstadoPresupuesto> estados = service.findAll();

        logger.info("Total estados encontrados: {}", estados.size());

        for (EstadoPresupuesto e : estados) {

            logger.info("{} - {}", e.getId(), e.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        EstadoPresupuestoServiceTest test =
                new EstadoPresupuestoServiceTest();

        test.testFindById(1L);

        test.testFindAll();
    }
}