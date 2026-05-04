package com.paula.checkmc.service;

import java.util.List;

import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.service.impl.PresupuestoEmpleadoServiceImpl;

public class PresupuestoEmpleadoServiceTest {

    private PresupuestoEmpleadoService service = null;

    public PresupuestoEmpleadoServiceTest() {
        this.service = new PresupuestoEmpleadoServiceImpl();
    }

   
    public void testCreate() {


        PresupuestoEmpleado pe = new PresupuestoEmpleado();

        pe.setEmpleadoId(1L);
        pe.setPresupuestoId(1L);

        boolean creado = service.create(pe);

        if (creado) {
            System.out.println("Relación creada correctamente");
        } else {
            System.out.println("Error al crear la relación");
        }
    }

   
 

   
    public void testFindEmpleadosByPresupuesto(Long presupuestoId) {


        List<Long> empleados = service.findEmpleadosByPresupuesto(presupuestoId);

        System.out.println("Empleados encontrados: " + empleados.size());
    }

    public void testDelete(Long empleadoId, Long presupuestoId) {


        boolean eliminado = service.delete(empleadoId, presupuestoId);

        if (eliminado) {
            System.out.println("Relación eliminada correctamente");
        } else {
            System.out.println("No se pudo eliminar la relación");
        }
    }

    public static void main(String[] args) {

        PresupuestoEmpleadoServiceTest test = new PresupuestoEmpleadoServiceTest();

        test.testCreate();

        //test.testFindPresupuestosByEmpleado(1L);

        //test.testFindEmpleadosByPresupuesto(1L);

        //test.testDelete(1L, 1L);
    }
}