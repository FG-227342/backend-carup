package com.nero.carupapi.model;

import jakarta.persistence.*;

@Entity
@Table(name="marcas")
public class Marca {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idMarca;
    private String nombre;

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
