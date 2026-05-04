package com.paula.checkmc.model;

import java.time.LocalDate;

public class OrdenTrabajo {

    private Long id;
    private String diagnostico;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Long presupuestoId;  
    private Long cocheId;        

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(Long id,
                        String diagnostico,
                        LocalDate fechaInicio,
                        LocalDate fechaFin,
                        Long presupuestoId,
                        Long cocheId) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuestoId = presupuestoId;
        this.cocheId = cocheId;
    }

    public Long getId() {
        return id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Long getPresupuestoId() {
        return presupuestoId;
    }

    public Long getCocheId() {
        return cocheId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setPresupuestoId(Long presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    public void setCocheId(Long cocheId) {
        this.cocheId = cocheId;
    }
}