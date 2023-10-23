package com.nero.carupapi.model;

import jakarta.persistence.*;

@Entity
@Table(name="choferes")
public class Chofer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idChofer;

    public Integer getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Integer idChofer) {
        this.idChofer = idChofer;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String celular;

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
