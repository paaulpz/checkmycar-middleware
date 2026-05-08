package com.paula.checkmc.model;

public class ClienteCriteria  extends AbstracValueObject {

    private final String __orderByDniNie__ = "c.dni_nie";
    private final String __orderByEmail__ = "c.email";
    private final String orderByName = "c.name";
    private final String __orderByFirstSurname__ = "c.first_surname";

    private String dniNie;
    private String email;
    private Long localidadId;
    private Long generoId;

    private String orderBy = orderByName;
    private boolean ascDesc = true;

    public ClienteCriteria() {
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

}



