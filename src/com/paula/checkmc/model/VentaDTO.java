package com.paula.checkmc.model;

import java.time.LocalDate;

public class VentaDTO {

    private Long id;
    private LocalDate fechaVenta;

    private Double precioCliente;
    private Double precioFinal;

    private Long clienteCompradorId;
    private String clienteCompradorNombre;

    private Long clienteVendedorId;
    private String clienteVendedorNombre;

    private Long empleadoId;
    private String empleadoNombre;

    private Long cocheId;
    private String matriculaCoche;

    // Getters & Setters

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

    public String getClienteCompradorNombre() {
        return clienteCompradorNombre;
    }

    public void setClienteCompradorNombre(String clienteCompradorNombre) {
        this.clienteCompradorNombre = clienteCompradorNombre;
    }

    public Long getClienteVendedorId() {
        return clienteVendedorId;
    }

    public void setClienteVendedorId(Long clienteVendedorId) {
        this.clienteVendedorId = clienteVendedorId;
    }

    public String getClienteVendedorNombre() {
        return clienteVendedorNombre;
    }

    public void setClienteVendedorNombre(String clienteVendedorNombre) {
        this.clienteVendedorNombre = clienteVendedorNombre;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        this.empleadoNombre = empleadoNombre;
    }

    public Long getCocheId() {
        return cocheId;
    }

    public void setCocheId(Long cocheId) {
        this.cocheId = cocheId;
    }

    public String getMatriculaCoche() {
        return matriculaCoche;
    }

    public void setMatriculaCoche(String matriculaCoche) {
        this.matriculaCoche = matriculaCoche;
    }
}