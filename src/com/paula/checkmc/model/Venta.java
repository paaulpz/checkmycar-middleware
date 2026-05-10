package com.paula.checkmc.model;

import java.time.LocalDate;

public class Venta {

    private Long id;
    private LocalDate fechaVenta;

    private Double precioCliente;
    private Double precioFinal;

    private Long clienteCompradorId;
    private Long clienteVendedorId;

    private Long empleadoId;
    private Long cocheId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getPrecioCliente() {
        return precioCliente;
    }

    public void setPrecioCliente(Double precioCliente) {
        this.precioCliente = precioCliente;
    }

    public Double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Long getClienteCompradorId() {
        return clienteCompradorId;
    }

    public void setClienteCompradorId(Long clienteCompradorId) {
        this.clienteCompradorId = clienteCompradorId;
    }

    public Long getClienteVendedorId() {
        return clienteVendedorId;
    }

    public void setClienteVendedorId(Long clienteVendedorId) {
        this.clienteVendedorId = clienteVendedorId;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getCocheId() {
        return cocheId;
    }

    public void setCocheId(Long cocheId) {
        this.cocheId = cocheId;
    }
}