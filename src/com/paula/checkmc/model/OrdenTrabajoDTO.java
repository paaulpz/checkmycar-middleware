package com.paula.checkmc.model;

import java.time.LocalDate;

public class OrdenTrabajoDTO  extends AbstracValueObject {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getPresupuestoId() {
		return presupuestoId;
	}

	public void setPresupuestoId(Long presupuestoId) {
		this.presupuestoId = presupuestoId;
	}

	public Long getCocheId() {
		return cocheId;
	}

	public void setCocheId(Long cocheId) {
		this.cocheId = cocheId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getMatriculaCoche() {
		return matriculaCoche;
	}

	public void setMatriculaCoche(String matriculaCoche) {
		this.matriculaCoche = matriculaCoche;
	}

	public String getEstadoPresupuesto() {
		return estadoPresupuesto;
	}

	public void setEstadoPresupuesto(String estadoPresupuesto) {
		this.estadoPresupuesto = estadoPresupuesto;
	}

    
}