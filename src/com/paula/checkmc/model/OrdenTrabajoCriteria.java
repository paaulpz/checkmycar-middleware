package com.paula.checkmc.model;

import java.time.LocalDate;

public class OrdenTrabajoCriteria {

    private Long presupuestoId;
    private Long cocheId;

    private LocalDate fechaInicioDesde;
    private LocalDate fechaInicioHasta;

    private LocalDate fechaFinDesde;
    private LocalDate fechaFinHasta;
    
    private String orderBy = null; 
    private boolean ascDesc = true;

    public OrdenTrabajoCriteria() {
    }

    public Long getPresupuestoId() {
        return presupuestoId;
    }

    public void setPresupuestoId(Long presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    public Long getCocheId() {
        return cocheId;
    }

    public void setCocheId(Long cocheId) {
        this.cocheId = cocheId;
    }

    public LocalDate getFechaInicioDesde() {
        return fechaInicioDesde;
    }

    public void setFechaInicioDesde(LocalDate fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }

    public LocalDate getFechaInicioHasta() {
        return fechaInicioHasta;
    }

    public void setFechaInicioHasta(LocalDate fechaInicioHasta) {
        this.fechaInicioHasta = fechaInicioHasta;
    }

    public LocalDate getFechaFinDesde() {
        return fechaFinDesde;
    }

    public void setFechaFinDesde(LocalDate fechaFinDesde) {
        this.fechaFinDesde = fechaFinDesde;
    }

    public LocalDate getFechaFinHasta() {
        return fechaFinHasta;
    }

    public void setFechaFinHasta(LocalDate fechaFinHasta) {
        this.fechaFinHasta = fechaFinHasta;
    }
    
    	public String getOrderBy() {
    		
    				return orderBy;
    	}

		public boolean isAscDesc() {
			return ascDesc;
		}

		public void setAscDesc(boolean ascDesc) {
			this.ascDesc = ascDesc;
		}

		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
    	
    	
    	}
