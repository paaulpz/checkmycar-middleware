package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.service.impl.ModeloServiceImpl;

public class ModeloServiceTest {

    private static final Logger logger =
            LogManager.getLogger(ModeloServiceTest.class);

    private ModeloService service = null;

    public ModeloServiceTest() {

        this.service = new ModeloServiceImpl();
    }

    public void testFindByMarca(Long marcaId) throws Exception {

        List<Modelo> modelos = service.findByMarca(marcaId);

        logger.info("Total modelos encontrados: {}", modelos.size());

        for (Modelo m : modelos) {

            logger.info("{} - {}", m.getId(), m.getNombre());
        }
    }

    public static void main(String[] args) throws Exception {

        ModeloServiceTest test = new ModeloServiceTest();

        test.testFindByMarca(1L);
    }
}