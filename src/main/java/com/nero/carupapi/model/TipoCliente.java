package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tipoCliente")
public class TipoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short idTipo;
    private String nombre;
    private Short idAgo;

    public Short getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Short idTipo) {
        this.idTipo = idTipo;
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
