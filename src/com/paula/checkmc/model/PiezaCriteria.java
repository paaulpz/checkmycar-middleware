package com.paula.checkmc.model;

public class PiezaCriteria {

    private String nombre;
    private Long estadoId;
    private Integer estadonombre;
    private String numeroReferencia;

    public PiezaCriteria() {
    }

    public String getNombre() { 
    	
    	return nombre;
    	}
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public Long getEstadoId() {
    	return estadoId;
    	
    }
    
    public void setEstadoId(Long estadoId) { 
    	this.estadoId = estadoId; 
    	}

	public Integer getEstadonombre() {
		return estadonombre;
	}

	public void setEstadonombre(Integer estadonombre) {
		this.estadonombre = estadonombre;
	}

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
    
    
}