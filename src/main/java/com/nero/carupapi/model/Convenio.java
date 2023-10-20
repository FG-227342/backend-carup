package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="convenios")
public class Convenio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idConvenio;
    private String nombre;
    private Byte idAgo;
    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @JsonIgnore
    public Byte getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Byte idAgo) {
        this.idAgo = idAgo;
    }

}
