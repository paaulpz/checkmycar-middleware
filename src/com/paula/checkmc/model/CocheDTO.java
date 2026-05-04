package com.paula.checkmc.model;

public class CocheDTO {

    private Long id;
    private String matricula;
    private String numeroBastidor;
    private Integer ano;
    private int potenciaCv;
    private double kilometros; 
    private String diagnostico;
    private double precioFinal;

    private Long clienteId;
    private String nombreCliente;

    private Long modeloId;
    private String nombreModelo;
    private String nombreMarca;

    private Long tipoCombustibleId;
    private String tipoCombustible;

    private Long tipoTransmisionId;
    private String tipoTransmision;

    private Long tipoMotorId;
    private String tipoMotor;

    public CocheDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getPotenciaCv() {
		return potenciaCv;
	}

	public void setPotenciaCv(int potenciaCv) {
		this.potenciaCv = potenciaCv;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Long getModeloId() {
		return modeloId;
	}

	public void setModeloId(Long modeloId) {
		this.modeloId = modeloId;
	}

	public String getNombreModelo() {
		return nombreModelo;
	}

	public void setNombreModelo(String nombreModelo) {
		this.nombreModelo = nombreModelo;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public Long getTipoCombustibleId() {
		return tipoCombustibleId;
	}

	public void setTipoCombustibleId(Long tipoCombustibleId) {
		this.tipoCombustibleId = tipoCombustibleId;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Long getTipoTransmisionId() {
		return tipoTransmisionId;
	}

	public void setTipoTransmisionId(Long tipoTransmisionId) {
		this.tipoTransmisionId = tipoTransmisionId;
	}

	public String getTipoTransmision() {
		return tipoTransmision;
	}

	public void setTipoTransmision(String tipoTransmision) {
		this.tipoTransmision = tipoTransmision;
	}

	public Long getTipoMotorId() {
		return tipoMotorId;
	}

	public void setTipoMotorId(Long tipoMotorId) {
		this.tipoMotorId = tipoMotorId;
	}

	public String getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}
		
	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	


}