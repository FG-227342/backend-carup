package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idCiudad;
    private String nombre;
    private Integer idPais;
    private Integer idAgo;

    public Object getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    @JsonIgnore
    public Integer getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Integer idAgo) {
        this.idAgo = idAgo;
    }

}
