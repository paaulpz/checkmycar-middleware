package com.paula.checkmc.model;

public class CocheCriteria {
	
	private final String orderBymatricula = "c.car_registration";
	private final String orderByNumeroBastidor = "c.chassis_number";
	private final String orderByPrecioFinal = "c.final_price";
	

    private String matricula;
    private String numeroBastidor; 
    private double precioFinal;
    private Double precioMin;
    private Double precioMax;
    private long marcaId;
    private Long clienteId;
    private Long modeloId;
    private Long tipoCombustibleId;
	private Long tipoTransmisionId;
	private Long tipoMotorId;
	private String nombreMotor; 
	private String nombreTransmision; 
	private String nombreCombustible; 
	private String orderBy = null; 
    private boolean ascDesc = true;
	
    public CocheCriteria() {
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

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(long marcaId) {
		this.marcaId = marcaId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getModeloId() {
		return modeloId;
	}

	public void setModeloId(Long modeloId) {
		this.modeloId = modeloId;
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

	public Long getTipoMotorId() {
		return tipoMotorId;
	}

	public void setTipoMotorId(Long tipoMotorId) {
		this.tipoMotorId = tipoMotorId;
	}

	public Double getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(Double precioMin) {
		this.precioMin = precioMin;
	}

	public Double getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(Double precioMax) {
		this.precioMax = precioMax;
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

	public String getOrderBymatricula() {
		return orderBymatricula;
	}

	public String getOrderByNumeroBastidor() {
		return orderByNumeroBastidor;
	}

	public String getOrderByPrecioFinal() {
		return orderByPrecioFinal;
	}

	public String getNombreMotor() {
		return nombreMotor;
	}

	public void setNombreMotor(String nombreMotor) {
		this.nombreMotor = nombreMotor;
	}

	public String getNombreTransmision() {
		return nombreTransmision;
	}

	public void setNombreTransmision(String nombreTransmision) {
		this.nombreTransmision = nombreTransmision;
	}

	public String getNombreCombustible() {
		return nombreCombustible;
	}

	public void setNombreCombustible(String nombreCombustible) {
		this.nombreCombustible = nombreCombustible;
	}


	
	
	

    
}