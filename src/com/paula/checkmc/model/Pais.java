package com.paula.checkmc.model;

public class Pais extends AbstracValueObject {
	private Long id;
	private String nombre;
	
	public Pais() {
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
