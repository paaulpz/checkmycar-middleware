package com.paula.checkmc.model;

import java.time.LocalDateTime;

public class CitaCriteria {
	
	private final String orderByDniNie = "c.dni_nie";
	private final String orderByEmail = "c.email";
	private final String orderByName = "c.name";
	private final String orderByFirstSurname = "c.first_surname";

    private Long clienteId;
    private Long cocheId;
    private Long estadoCitaId;

    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    
    private String orderBy = null; 
    private boolean ascDesc = true;

    public CitaCriteria() {
    }

    public Long getClienteId() {
    	return clienteId; 
    	}
    
    public void setClienteId(Long clienteId) { 
    	this.clienteId = clienteId; 
    	}

    public Long getCocheId() {
    	return cocheId; 
    	}
    
    public void setCocheId(Long cocheId) { 
    	this.cocheId = cocheId;
    }

    public Long getEstadoCitaId() {
    	return estadoCitaId;
    	}
    
    public void setEstadoCitaId(Long estadoCitaId) {
    	this.estadoCitaId = estadoCitaId;
    	}

    public LocalDateTime getFechaDesde() {
    	return fechaDesde;
    	}
    
    public void setFechaDesde(LocalDateTime fechaDesde) { 
    	this.fechaDesde = fechaDesde; 
    	}

    public LocalDateTime getFechaHasta() { 
    	return fechaHasta;
    	}
    
    public void setFechaHasta(LocalDateTime fechaHasta) { 
    	this.fechaHasta = fechaHasta;
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

	public String getOrderByDniNie() {
		return orderByDniNie;
	}

	public String getOrderByEmail() {
		return orderByEmail;
	}

	public String getOrderByName() {
		return orderByName;
	}

	public String getOrderByFirstSurname() {
		return orderByFirstSurname;
	}
    
	
    
}