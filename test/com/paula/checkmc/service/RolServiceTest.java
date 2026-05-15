package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.service.impl.RolServiceImpl;

public class RolServiceTest {

    private static final Logger logger =
            LogManager.getLogger(RolServiceTest.class);

    private RolService service = null;

    public RolServiceTest() {

        this.service = new RolServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        Rol rol = service.findById(id);

        if (rol != null) {

            logger.info("Id: {}", rol.getId());
            logger.info("Nombre: {}", rol.getNombre());

        } else {

            logger.warn("No se encontró el rol");
        }
    }

    public void testFindAll() throws Exception {

        List<Rol> roles = service.findAll();

        logger.info("Total roles encontrados: {}", roles.size());

        for (Rol r : roles) {

            logger.info("{} - {}", r.getId(), r.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        RolServiceTest test = new RolServiceTest();

        test.testFindById(1L);

        // test.testFindAll();
    }
}