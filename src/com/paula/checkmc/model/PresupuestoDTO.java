package com.paula.checkmc.model;

import java.time.LocalDate;

public class PresupuestoDTO  extends AbstracValueObject{

    private Long id;
    private LocalDate fecha;
    private Double precioFinal;

   
    private Long clienteId;
    private Long estadoPresupuestoId;

    
    private String nombreCliente;
    private String estadoPresupuesto;
    private String nombre;

    public PresupuestoDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
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

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getEstadoPresupuesto() {
		return estadoPresupuesto;
	}

	public void setEstadoPresupuesto(String estadoPresupuesto) {
		this.estadoPresupuesto = estadoPresupuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    
}