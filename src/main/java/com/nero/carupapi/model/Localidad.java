package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="localidades")
public class Localidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idLocalidad;
    private String nombre;
    private Integer idCiudad;

    private Integer idAgo;

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
    @JsonIgnore
    public Integer getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Integer idAgo) {
        this.idAgo = idAgo;
    }

}
