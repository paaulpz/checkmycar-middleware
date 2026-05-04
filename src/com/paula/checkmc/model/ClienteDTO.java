package com.paula.checkmc.model;

public class ClienteDTO {

    private Long id;
    private String dniNie;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String telefono;
    private String direccion;
    private String password;

  

    private Long localidadId;
    private Long generoId;

    private String nombreLocalidad;
    private String nombreGenero;

    public ClienteDTO() {
    }

    public Long getId() { 
    	return id; 
    	}
    public void setId(Long id) { 
    	this.id = id; 
    	}

    public String getDniNie() {
    	return dniNie;
    	}
    public void setDniNie(String dniNie) { 
    	this.dniNie = dniNie;
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
    

    public Long getLocalidadId() {
    	return localidadId; 
    	
    }
    
    public void setLocalidadId(Long localidadId) {
    	this.localidadId = localidadId;
    	
    }

    public Long getGeneroId() { 
    	return generoId;
    	
    }
    
    public void setGeneroId(Long generoId) { 
    	this.generoId = generoId;
    	
    }

    public String getNombreLocalidad() {
    	return nombreLocalidad;
    	
    }
    
    public void setNombreLocalidad(String nombreLocalidad) {
    	this.nombreLocalidad = nombreLocalidad;
    	
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    

    public String getNombreGenero() {
    	return nombreGenero;
    	}
    
    public void setNombreGenero(String nombreGenero) { 
    	this.nombreGenero = nombreGenero;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
	    String dni = this.dniNie != null ? this.dniNie : "";
	    String nombre = this.nombre != null ? this.nombre : "";
	    return dni + " - " + nombre;
	}
    
	
    
}