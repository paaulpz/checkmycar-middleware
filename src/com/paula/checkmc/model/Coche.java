package com.paula.checkmc.model;

public class Coche {

    private Long id;
    private String diagnostico;
    private String matricula;
    private String numeroBastidor;
    private Integer ano;
    private long potenciaCv;
    private long kilometros;
    private Double precioFinal = null;

    private Long clienteId;
    private Long marcaId;
    private Long tipoCombustibleId;
    private Long tipoTransmisionId;
    private Long modeloId;
    private Long tipoMotorId;

    public Coche() {
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNumeroBastidor() {
        return numeroBastidor;
    }

    public void setNumeroBastidor(String numeroBastidor) {
        this.numeroBastidor = numeroBastidor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public long getPotenciaCv() {
        return potenciaCv;
    }

    public void setPotenciaCv(long potenciaCv) {
        this.potenciaCv = potenciaCv;
    }

    public long getKilometros() {
        return kilometros;
    }

    public void setKilometros(long kilometros) {
        this.kilometros = kilometros;
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

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Long getTipoCombustibleId() {
        return tipoCombustibleId;
    }

    public void setTipoCombustibleId(Long tipoCombustibleId) {
        this.tipoCombustibleId = tipoCombustibleId;
    }

    public Long getTipoTransmisionId() {
        return tipoTransmisionId;
    }

    public void setTipoTransmisionId(Long tipoTransmisionId) {
        this.tipoTransmisionId = tipoTransmisionId;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modeloId) {
        this.modeloId = modeloId;
    }

    public Long getTipoMotorId() {
        return tipoMotorId;
    }

    public void setTipoMotorId(Long tipoMotorId) {
        this.tipoMotorId = tipoMotorId;
    }
}