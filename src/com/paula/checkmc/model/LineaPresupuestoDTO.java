package com.paula.checkmc.model;

public class LineaPresupuestoDTO extends AbstracValueObject {

    private Long id;
    private Double unidades;
    private Double precioUnitario;
    private Double precioFinal;

    private Long piezaId;
    private Long presupuestoId;

    private String nombrePieza;

    public LineaPresupuestoDTO() {
    }

    public Long getId() { 
    	return id;
    	}
    
    public Double getUnidades() {
    	return unidades;
    	}
    
    public Double getPrecioUnitario() {
    	return precioUnitario;
    	}
    public Double getPrecioFinal() {
    	return precioFinal; 
    	}
    
    public Long getPiezaId() { 
    	
    	return piezaId; 
    	}
    
    public Long getPresupuestoId() { 
    	return presupuestoId; 
    	}
    
    public String getNombrePieza() {
    	return nombrePieza; 
    	}

    
    public void setId(Long id) {
    	this.id = id;
		}
    	
    public void setUnidades(Double unidades) {
		this.unidades = unidades;
		}
    	
    public void setPrecioUnitario(Double precioUnitario) {
    	
    			this.precioUnitario = precioUnitario;
    			
    }
    
    public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal; 
		}
    
    public void setPiezaId(Long piezaId) {
		this.piezaId = piezaId;
		}
    
    public void setPresupuestoId(Long presupuestoId) {
    			this.presupuestoId = presupuestoId;
    				
    }
    
    public void setNombrePieza(String nombrePieza) {
		this.nombrePieza = nombrePieza;
		
    }


    
    	
    
    
}