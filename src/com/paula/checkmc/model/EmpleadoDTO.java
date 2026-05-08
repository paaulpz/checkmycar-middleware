package com.paula.checkmc.model;

public class EmpleadoDTO extends AbstracValueObject {

    private Long id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;

    private String dniNie;
    private String direccion; 
   
    private String email;
    private String telefono;

    private Long rolId;
    private String nombreRol;

    private Long generoId;
    private String nombreGenero;

    private Long localidadId;
    private String nombreLocalidad;
    
    private String password;

    public EmpleadoDTO() {
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

    public String getPrimerApellido() { 
    	return primerApellido;
    	}
    
    public void setPrimerApellido(String primerApellido) {
    	this.primerApellido = primerApellido;
    	
    }

    public String getSegundoApellido() {
    	return segundoApellido;
    	}
    
    public void setSegundoApellido(String segundoApellido) { 
    	this.segundoApellido = segundoApellido;
    	}

    public String getDniNie() { 
    	return dniNie;
    	}
    
    public void setDniNie(String dniNie) { 
    	this.dniNie = dniNie; 
    	
    }

   



    public String getEmail() {
    	return email;
    	
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    	
    }

    public String getTelefono() {
    	
    	return telefono; 
    	}
    
    public void setTelefono(String telefono) { 
    	
    	this.telefono = telefono; 
    	}

    public Long getRolId() { 
    	return rolId; 
    	
    }
    public void setRolId(Long rolId) { 
    	
    	this.rolId = rolId; 
    	
    }

    public String getNombreRol() { 
    	return nombreRol;
    	
    }
    
    public void setNombreRol(String nombreRol) { 
    	
    	this.nombreRol = nombreRol; 
    	}

    public Long getGeneroId() {
    	
    	return generoId;
    	}
    
    public void setGeneroId(Long generoId) { 
    	
    	this.generoId = generoId;
    }
    

    public String getNombreGenero() { 
    	
    	return nombreGenero; 
    	}
    
    public void setNombreGenero(String nombreGenero) {
    	this.nombreGenero = nombreGenero;
    	}
    

    public Long getLocalidadId() { 
    	
    	return localidadId; 
    	
    }
    public void setLocalidadId(Long localidadId) { 
    	this.localidadId = localidadId; 
    	
    }

    public String getNombreLocalidad() { 
    	return nombreLocalidad;
    	}
    
    
    public void setNombreLocalidad(String nombreLocalidad) { 
    	this.nombreLocalidad = nombreLocalidad;
    
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

    
    
	
}