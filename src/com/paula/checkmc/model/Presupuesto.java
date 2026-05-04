package com.paula.checkmc.model;

import java.time.LocalDate;

public class Presupuesto {

    private Long id;
    private LocalDate fecha;
    private Long estadoPresupuestoId;
    private Long clienteId;
    private String nombre;
    private Double precioFinal;

    public Presupuesto() {
    }

    public Long getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public Long getEstadoPresupuestoId() { return estadoPresupuestoId; }
    public Long getClienteId() { return clienteId; }
    public String getNombre() { return nombre; }
    public Double getPrecioFinal() { return precioFinal; }

    public void setId(Long id) { this.id = id; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setEstadoPresupuestoId(Long estadoPresupuestoId) { this.estadoPresupuestoId = estadoPresupuestoId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecioFinal(Double precioFinal) { this.precioFinal = precioFinal; }
}