package com.paula.checkmc.model;

public class Empleado {

    private Long id;
    private String nombre;
    private String dniNie;
   
   

    private Long rolId;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private Long generoId;
    private String password;
    private Long localidadId;
    private String direccion;
    private String telefono;

    public Empleado() {
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

    public String getDniNie() { 
    	return dniNie;
    	
    }
    
    public void setDniNie(String dniNie) { 
    	this.dniNie = dniNie; 
    	
    }

    
    

    public Long getRolId() {
    	return rolId; 
    	}
    
    public void setRolId(Long rolId) { 
    	this.rolId = rolId; 
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

    public Long getGeneroId() {
    	return generoId;
    }
    
    public void setGeneroId(Long generoId) { 
    	
    	this.generoId = generoId; 
    	}

    public String getPassword() {
    	
    	return password; 
    	
    }
    
    public void setPassword(String password) {
    	
    	this.password = password; 
    	
    }
    

    public Long getLocalidadId() {
    	
    	return localidadId;
    	
    }
    
    public void setLocalidadId(Long localidadId) { 
    	
    	this.localidadId = localidadId; 
    
    }
    
    

    public String getDireccion() {
    	return direccion;
    	
    }
    
    public void setDireccion(String direccion) { 
    	
    	this.direccion = direccion; 
    	
    }

    public String getTelefono() {
    	return telefono;
    	
    }
    
    
    public void setTelefono(String telefono) { 
    	
    	this.telefono = telefono; 
    
    }
	
}