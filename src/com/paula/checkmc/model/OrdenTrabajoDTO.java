package com.paula.checkmc.model;

import java.time.LocalDate;

public class OrdenTrabajoDTO {

    private Long id;
    private String diagnostico;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

 
    private Long presupuestoId;
    private Long cocheId;

   
    private String nombreCliente;
    private String matriculaCoche;
    private String estadoPresupuesto;

    public OrdenTrabajoDTO() {
    }

    public Long getId() { return id; }
    public String getDiagnostico() { return diagnostico; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public Long getPresupuestoId() { return presupuestoId; }
    public Long getCocheId() { return cocheId; }
    public String getNombreCliente() { return nombreCliente; }
    public String getMatriculaCoche() { return matriculaCoche; }
    public String getEstadoPresupuesto() { return estadoPresupuesto; }

    public void setId(Long id) { this.id = id; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public void setPresupuestoId(Long presupuestoId) { this.presupuestoId = presupuestoId; }
    public void setCocheId(Long cocheId) { this.cocheId = cocheId; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setMatriculaCoche(String matriculaCoche) { this.matriculaCoche = matriculaCoche; }
    public void setEstadoPresupuesto(String estadoPresupuesto) { this.estadoPresupuesto = estadoPresupuesto; }
}