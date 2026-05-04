package com.paula.checkmc.model;

public class Cliente {

    private Long id;
    private String dniNie = "";
    private String nombre = "";
    private String primerApellido ="";
    private String segundoApellido;
    private String email;
    private Long localidadId;
    private Long generoId;
    private String password ;
    private String direccion;
    private String telefono;

    public Cliente() {
    }

    public Long getId() {
    	return id;
    	}
    
    public String getDniNie() {
    	return dniNie; 
    	}
    
    public String getNombre() { 
    	return nombre;
    	}
    
    public String getPrimerApellido() { 
    	return primerApellido; 
    }
    
    public String getSegundoApellido() { 
    	return segundoApellido; 
    }
    
    public String getEmail() { 
    	return email;
    }
    
    public Long getLocalidadId() { 
    	return localidadId; 
    	}
    
    public Long getGeneroId() { 
    	return generoId;
    }
    
    public String getPassword() { 
    	return password;
    	}
    
    public String getDireccion() { 
    	return direccion;
    	}
    
    public String getTelefono() { 
    	return telefono;
    	}

    public void setId(Long id) {
    	this.id = id;
    	}
    
    public void setDniNie(String dniNie) { 
    	this.dniNie = dniNie; 
    	}
    
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	}
   
    
    public void setPrimerApellido(String primerApellido) {
    	this.primerApellido = primerApellido; 
    	}
    
    public void setSegundoApellido(String segundoApellido) { 
    	this.segundoApellido = segundoApellido;
    	}
    
    public void setEmail(String email) { 
    	this.email = email; 
    	}
    
    public void setLocalidadId(Long localidadId) {
    	this.localidadId = localidadId; 
    	}
    
    public void setGeneroId(Long generoId) { 
    	this.generoId = generoId; 
    	}
    
    public void setPassword(String password) {
    	this.password = password;
    	}
    
    public void setDireccion(String direccion) { 
    	this.direccion = direccion; 
    }
    
    public void setTelefono(String telefono) { 
    	this.telefono = telefono;
    	}
    
    @Override
    public String toString() {
        return dniNie + " - " + nombre;
    }

	
    
}