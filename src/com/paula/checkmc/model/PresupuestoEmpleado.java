package com.paula.checkmc.model;

public class PresupuestoEmpleado {

    private Long empleadoId;
    private Long presupuestoId;

    public PresupuestoEmpleado() {
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getPresupuestoId() {
        return presupuestoId;
    }

    public void setPresupuestoId(Long presupuestoId) {
        this.presupuestoId = presupuestoId;
    }
}