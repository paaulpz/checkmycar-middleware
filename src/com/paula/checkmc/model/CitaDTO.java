package com.paula.checkmc.model;

import java.time.LocalDateTime;

public class CitaDTO extends AbstracValueObject {

    private Long id;
    private String descripcion;
    private LocalDateTime fecha;

    private Long clienteId;
    private Long cocheId;
    private Long estadoCitaId;

    private String nombreCliente;
    private String matriculaCoche;
    private String estadoCita;

    public CitaDTO() {
    }

    public Long getId() { 
    	return id; 
    	}
    
    public String getDescripcion() { 
    	return descripcion; 
    	}
    
    public LocalDateTime getFecha() {
    	return fecha; 
    	}
    
    public Long getClienteId() { 
    	return clienteId;
    	}
    
    public Long getCocheId() { 
    	return cocheId;
    }
    
    public Long getEstadoCitaId() { 
    	return estadoCitaId; 
    	}
    
    public String getNombreCliente() { 
    	return nombreCliente; 
    }
    
    public String getMatriculaCoche() {
    	return matriculaCoche;
    	}
    
    public String getEstadoCita() { 
    	return estadoCita;
    	}
    

    public void setId(Long id) {
    	this.id = id; 
    	}
    
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion; 
    	}
    
    public void setFecha(LocalDateTime fecha) { 
    	this.fecha = fecha;
    	}
    
    public void setClienteId(Long clienteId) {
    	this.clienteId = clienteId;
    	}
    
    public void setCocheId(Long cocheId) { 
    	this.cocheId = cocheId; 
    }
    
    
    public void setEstadoCitaId(Long estadoCitaId) { 
    	this.estadoCitaId = estadoCitaId;
    	}
    
    public void setNombreCliente(String nombreCliente) { 
    	this.nombreCliente = nombreCliente;
    }
    
    public void setMatriculaCoche(String matriculaCoche) { 
    	this.matriculaCoche = matriculaCoche; 
    	}
    
    
    public void setEstadoCita(String estadoCita) {
    	this.estadoCita = estadoCita; 
    	}
}