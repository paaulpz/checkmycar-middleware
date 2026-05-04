package com.paula.checkmc.model;

public class Localidad {

    private Long id;
    private String nombre;
    private Long provinceId;

    public Localidad() {
    }

    public Localidad(Long id, String nombre, Long provinceId) {
        this.id = id;
        this.nombre = nombre;
        this.provinceId = provinceId;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}