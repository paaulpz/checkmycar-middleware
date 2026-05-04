package com.paula.checkmc.model;

public class Genero {

	public static final Long MASCULINO = 1L;
	public static final Long FEMENINO = 2L;
	public static final Long OTRO_LONG = 3L; 	
	
	
	private Long id;
	private String nombre;

	public Genero() {
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


}
			