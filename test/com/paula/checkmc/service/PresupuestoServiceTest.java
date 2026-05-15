package com.paula.checkmc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.PresupuestoCriteria;
import com.paula.checkmc.model.PresupuestoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.impl.PresupuestoServiceImpl;

public class PresupuestoServiceTest {

    private static final Logger logger =
            LogManager.getLogger(PresupuestoServiceTest.class);

    private PresupuestoService service = null;

    public PresupuestoServiceTest() {

        this.service = new PresupuestoServiceImpl();
    }

    /*
    public void testCreate() throws Exception {

        PresupuestoDTO p = new PresupuestoDTO();

        p.setFecha(LocalDate.now());
        p.setPrecioFinal(150.0);
        p.setClienteId(1L);
        p.setEstadoPresupuestoId(1L);
        p.setNombre("Presupuesto prueba");

        Long id = service.create(p);

        if (id != null) {

            logger.info("Presupuesto creado con ID: {}", id);

        } else {

            logger.warn("Error al crear el presupuesto");
        }
    }
    */

    public void testFindById(Long id) throws Exception {

        PresupuestoDTO p = service.findById(id);

        if (p != null) {

            logger.info("Id: {}", p.getId());
            logger.info("Nombre: {}", p.getNombre());

        } else {

            logger.warn("No se encontró el presupuesto");
        }
    }

    public void testFindByCriteria() throws Exception {

        PresupuestoCriteria criteria = new PresupuestoCriteria();

        criteria.setClienteId(1L);

        Results<PresupuestoDTO> results =
                service.findByCriteria(criteria, 1, 10);

        logger.info("Total presupuestos encontrados: {}",
                results.getTotal());

        for (PresupuestoDTO p : results.getPage()) {

            logger.info("{}", p);
        }
    }

    public void testDelete(Long id) throws Exception {

        boolean eliminado = service.delete(id);

        if (eliminado) {

            logger.info("Presupuesto eliminado correctamente");

        } else {

            logger.warn("No se pudo eliminar el presupuesto");
        }
    }

    public static void main(String[] args) throws Exception {

        PresupuestoServiceTest test =
                new PresupuestoServiceTest();

        // test.testCreate();

        test.testFindById(1L);

        // test.testFindByCriteria();

        // test.testDelete(1L);
    }
}