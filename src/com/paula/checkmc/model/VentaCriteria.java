package com.paula.checkmc.model;

import java.time.LocalDate;

public class VentaCriteria {

    private Long clienteCompradorId;
    private Long clienteVendedorId;
    private Long empleadoId;
    private Long cocheId;

    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

   
    private Boolean soloAbiertas;


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

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Boolean getSoloAbiertas() {
        return soloAbiertas;
    }

    public void setSoloAbiertas(Boolean soloAbiertas) {
        this.soloAbiertas = soloAbiertas;
    }
}