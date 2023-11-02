package com.nero.carupapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prestadores")
public class Prestador {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Short idPrestador;
    private String nombre;
    private String descripcion;

    public Prestador(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Prestador() {
    }

    public Short getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Short idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
