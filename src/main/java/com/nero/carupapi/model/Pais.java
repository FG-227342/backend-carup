package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Short idPais;
    private String nombre;
    private Short idAgo;

    public Short getIdPais() {
        return idPais;
    }

    public void setIdPais(Short idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @JsonIgnore
    public Short getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Short idAgo) {
        this.idAgo = idAgo;
    }

}
