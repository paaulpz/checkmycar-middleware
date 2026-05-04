package com.paula.checkmc.service;

import com.paula.checkmc.dao.EstadoPresupuestoDAO;
import com.paula.checkmc.model.EstadoPresupuesto;

public class EstadoPresupuestoServiceTest {

    public static void testFindById() {
        try {
            EstadoPresupuestoDAO dao = new EstadoPresupuestoDAO();

            EstadoPresupuesto estado = dao.findById(1L);

            if (estado != null) {
                System.out.println("ID: " + estado.getId());
                System.out.println("Nombre: " + estado.getNombre());
            } else {
                System.out.println("Estado no encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testFindById();
    }
}