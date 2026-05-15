package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.model.PiezaDTO;
import com.paula.checkmc.service.impl.PiezaServiceImpl;

public class PiezaServiceTest {

    private static final Logger logger =
            LogManager.getLogger(PiezaServiceTest.class);

    private PiezaService service = null;

    public PiezaServiceTest() {

        this.service = new PiezaServiceImpl();
    }

    public void testFindById(Long id) throws Exception {

        PiezaDTO pieza = service.findById(id);

        if (pieza != null) {

            logger.info("Id: {}", pieza.getId());
            logger.info("Nombre: {}", pieza.getNombre());

        } else {

            logger.warn("No se encontró la pieza");
        }
    }

    public void testFindAll() throws Exception {

        List<PiezaDTO> piezas = service.findAll();

        logger.info("Total piezas encontradas: {}", piezas.size());

        for (PiezaDTO p : piezas) {

            logger.info("{}", p);
        }
    }

    public void testFindByCriteria() throws Exception {

        PiezaCriteria criteria = new PiezaCriteria();

        criteria.setNombre("Filtro");

        List<PiezaDTO> piezas =
                service.findByCriteria(criteria, 0, 10);

        logger.info("Resultados encontrados: {}", piezas.size());

        for (PiezaDTO p : piezas) {

            logger.info("{}", p);
        }
    }

    public static void main(String[] args) throws Exception {

        PiezaServiceTest test = new PiezaServiceTest();

        test.testFindById(1L);

        // test.testFindAll();

        // test.testFindByCriteria();
    }
}