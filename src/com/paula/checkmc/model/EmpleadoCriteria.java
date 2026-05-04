package com.paula.checkmc.model;

public class EmpleadoCriteria {
	
	private final String orderByDniNie = "c.dni_nie";
	private final String orderByEmail = "c.email";
	private final String orderByName = "c.name";
	private final String orderByFirstSurname = "c.first_surname";
	
	private Integer id = null;
	private String nombre = null;

	private String dniNie = null;
	private String email = null;
	private String segundoApellido = null;
	private String primerApellido = null;
	private String telefono = null;
	private String direccion = null;	
	private Integer rolId = null;
	private Integer localidadId = null;
	private Integer generoId;
	private String nombreRol; 
	

	
	private String orderBy = null; 
    private boolean ascDesc = true;
	
	public EmpleadoCriteria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public Integer getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(Integer localidadId) {
		this.localidadId = localidadId;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	

	
	public Integer getGeneroId() {
	    return generoId;
	}

	public void setGeneroId(Integer generoId) {
	    this.generoId = generoId;
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

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	
	
	
	
}
