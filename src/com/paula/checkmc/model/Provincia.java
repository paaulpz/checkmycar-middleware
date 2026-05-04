package com.paula.checkmc.model;

public class Provincia extends AbstracValueObject {
	
	private Long id;
	private String nombre;
	private Long paisId;
	
	public Provincia() {
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

	public Long getPaisId() {
		return paisId;
	}

	public void setPaisId(Long paisId) {
		this.paisId = paisId;
	}
	

}
