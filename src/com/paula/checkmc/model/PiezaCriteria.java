package com.paula.checkmc.model;

public class PiezaCriteria {

    private String nombre;
    private Long estadoId;

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
}