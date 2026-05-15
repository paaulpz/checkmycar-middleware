package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.service.impl.PresupuestoEmpleadoServiceImpl;

public class PresupuestoEmpleadoServiceTest {

    private static final Logger logger =
            LogManager.getLogger(PresupuestoEmpleadoServiceTest.class);

    private PresupuestoEmpleadoService service = null;

    public PresupuestoEmpleadoServiceTest() {

        this.service = new PresupuestoEmpleadoServiceImpl();
    }

    public void testCreate() throws Exception {

        PresupuestoEmpleado pe = new PresupuestoEmpleado();

        pe.setEmpleadoId(1L);
        pe.setPresupuestoId(1L);

        boolean creado = service.create(pe);

        if (creado) {

            logger.info("Relación creada correctamente");

        } else {

            logger.warn("Error al crear la relación");
        }
    }

    public void testFindEmpleadosByPresupuesto(Long presupuestoId) throws Exception {

        List<Long> empleados =
                service.findEmpleadosByPresupuesto(presupuestoId);

        logger.info("Empleados encontrados: {}", empleados.size());

        for (Long id : empleados) {

            logger.info("Empleado id: {}", id);
        }
    }

    public void testDelete(Long empleadoId, Long presupuestoId) throws Exception {

        boolean eliminado =
                service.delete(empleadoId, presupuestoId);

        if (eliminado) {

            logger.info("Relación eliminada correctamente");

        } else {

            logger.warn("No se pudo eliminar la relación");
        }
    }

    public static void main(String[] args) throws Exception {

        PresupuestoEmpleadoServiceTest test =
                new PresupuestoEmpleadoServiceTest();

        test.testCreate();

        // test.testFindEmpleadosByPresupuesto(1L);

        // test.testDelete(1L, 1L);
    }
}