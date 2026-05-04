package com.paula.checkmc.model;

public class LineaPresupuestoCriteria {

    private Long id;
    private Long piezaId;
    private Long presupuestoId;

    private Double unidadesMin;
    private Double unidadesMax;

    private Double precioMin;
    private Double precioMax;
    
    private String orderBy = null; 
    private boolean ascDesc = true;

    public LineaPresupuestoCriteria() {
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getPiezaId() {
        return piezaId;
    }

    public void setPiezaId(Long piezaId) {
        this.piezaId = piezaId;
    }

    
    public Long getPresupuestoId() {
        return presupuestoId;
    }

    public void setPresupuestoId(Long presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    
    public Double getUnidadesMin() {
        return unidadesMin;
    }

    public void setUnidadesMin(Double unidadesMin) {
        this.unidadesMin = unidadesMin;
    }

    public Double getUnidadesMax() {
        return unidadesMax;
    }

    public void setUnidadesMax(Double unidadesMax) {
        this.unidadesMax = unidadesMax;
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
	    
	    	
	    
}