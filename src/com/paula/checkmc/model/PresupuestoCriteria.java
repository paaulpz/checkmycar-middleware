package com.paula.checkmc.model;

import java.time.LocalDate;

public class PresupuestoCriteria {

    private Long clienteId;
    private Long estadoPresupuestoId;

    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    
    private String cocheMatricula;
    private String nombreEmpleado;
    private String nombreCliente;
    private String estadoPresupuestoNombre;
    private String cocheModelo;
    
    private String orderBy = null; 
    private boolean ascDesc = true;
    
    public PresupuestoCriteria() {
    }

    public Long getClienteId() { 
    	return clienteId; 
    	}
    public void setClienteId(Long clienteId) { 
    	this.clienteId = clienteId; 
    	}

    public Long getEstadoPresupuestoId() { 
    	return estadoPresupuestoId; 
    	}
    public void setEstadoPresupuestoId(Long estadoPresupuestoId) { 
    	this.estadoPresupuestoId = estadoPresupuestoId;
    	}

    public LocalDate getFechaDesde() { 
    	return fechaDesde; 
    	}
    public void setFechaDesde(LocalDate fechaDesde) {
    	this.fechaDesde = fechaDesde; 
    	}

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) {
    	this.fechaHasta = fechaHasta;
    	}

	public String getCocheMatricula() {
		return cocheMatricula;
	}

	public void setCocheMatricula(String cocheMatricula) {
		this.cocheMatricula = cocheMatricula;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public String getEstadoPresupuestoNombre() {
		return estadoPresupuestoNombre;
	}
	
	public void setEstadoPresupuestoNombre(String estadoPresupuestoNombre) {
		this.estadoPresupuestoNombre = estadoPresupuestoNombre;
	}
	
	public String getCocheModelo() {
		return cocheModelo;
	}
	
	public void setCocheModelo(String cocheModelo) {
		this.cocheModelo = cocheModelo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(boolean ascDesc) {
		this.ascDesc = ascDesc;
	}
	
	
    
    
}