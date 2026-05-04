package com.paula.checkmc.model;

import java.math.BigDecimal;

public class Pieza {

    private Long id;
    private String nombre;
    private Integer stock;
    private Long estadoId;
    private BigDecimal precio;

    public Pieza() {
    }

    public Long getId() { 
    	return id; 
    	}
    
    public void setId(Long id) {
    	this.id = id;
    	}

    public String getNombre() { 
    	return nombre; 
    	}
    public void setNombre(String nombre) {
    	this.nombre = nombre; 
    	
    }

    public Integer getStock() {
    	return stock; 
    	
    }
    public void setStock(Integer stock) { 
    	this.stock = stock;
    	}

    public Long getEstadoId() { 
    	return estadoId; 
    	
    }
    
    public void setEstadoId(Long estadoId) { 
    	this.estadoId = estadoId;
    	
    }

    public BigDecimal getPrecio() { 
    	return precio;
    	}
    
    public void setPrecio(BigDecimal precio) {
    	this.precio = precio; 
    }
}