package com.paula.checkmc.model;

import java.math.BigDecimal;

public class PiezaDTO {

    private Long id;
    private String nombre;
    private Integer stock;
    private Long estadoId;
    private String nombreEstado;
    private BigDecimal precio;

    public PiezaDTO() {
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

    public String getNombreEstado() { 
    	
    	return nombreEstado; 
    }
    
    
    public void setNombreEstado(String nombreEstado) {
    	this.nombreEstado = nombreEstado;
    	
    }

    public BigDecimal getPrecio() { 
    	return precio;
      
     }
    
    
    public void setPrecio(BigDecimal precio) { 
    	    this.precio = precio; 
    
    }
}