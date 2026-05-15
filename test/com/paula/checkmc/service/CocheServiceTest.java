package com.paula.checkmc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Coche;
import com.paula.checkmc.model.CocheCriteria;
import com.paula.checkmc.model.CocheDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.CocheServiceImpl;

public class CocheServiceTest {

    private static final Logger logger =
            LogManager.getLogger(CocheServiceTest.class);

    private CocheService service = null;

    public CocheServiceTest() {

        this.service = new CocheServiceImpl();
    }

    public void testCreate() throws Exception {

        Coche coche = new Coche();

        coche.setDiagnostico("Revision general");
        coche.setMatricula("1234ABC");
        coche.setNumeroBastidor("VF1ABC12345678901");
        coche.setAno(2018);
        coche.setPotenciaCv(120);
        coche.setKilometros(85000);

        coche.setClienteId(1L);
        coche.setModeloId(1L);
        coche.setTipoCombustibleId(1L);
        coche.setTipoTransmisionId(1L);
        coche.setTipoMotorId(1L);

        Long id = service.create(coche);

        if (id != null) {

            logger.info("Coche creado correctamente con ID: {}", id);

        } else {

            logger.warn("Error al crear coche");
        }
    }

    public void testFindByCriteria() throws Exception {

        CocheCriteria criteria = new CocheCriteria();

        criteria.setMatricula("1234ABC");

        Results<CocheDTO> results =
                service.findByCriteria(criteria, 1, 10);

        print(results);
    }

    public void testDelete(Long id) throws Exception {

        boolean eliminado = service.delete(id);

        if (eliminado) {

            logger.info("Coche eliminado correctamente");

        } else {

            logger.warn("No se pudo eliminar el coche");
        }
    }

    public void testPageFindBy() throws Exception {

        CocheCriteria criteria = new CocheCriteria();

        int pageSize = 5;

        Results<CocheDTO> results = null;

        int from = 1;

        do {

            results = service.findByCriteria(criteria, from, pageSize);

            print(results);

            from = from + pageSize;

        } while (from <= results.getTotal());
    }

    private void print(Results<CocheDTO> results) {

        logger.info("Imprimiendo página...");
        logger.info("Total resultados: {}", results.getTotal());

        for (CocheDTO c : results.getPage()) {

            logger.info("{}", c);
        }
    }

    public static void main(String[] args) throws Exception {

        CocheServiceTest test = new CocheServiceTest();

        // test.testCreate();

        test.testPageFindBy();

        // test.testFindByCriteria();

        // test.testDelete(1L);
    }
}