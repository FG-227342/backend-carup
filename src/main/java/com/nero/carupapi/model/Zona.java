package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="zonas")
public class Zona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idZona;
    private String nombre;
    private Integer idLocalidad;
    private Integer idAgo;

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }
    @JsonIgnore
    public Integer getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Integer idAgo) {
        this.idAgo = idAgo;
    }

}
