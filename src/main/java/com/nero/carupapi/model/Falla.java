package com.nero.carupapi.model;

import jakarta.persistence.*;

@Entity
@Table(name="fallas")
public class Falla {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idFalla;
    private String nombre;

    public Integer getIdFalla() {
        return idFalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
