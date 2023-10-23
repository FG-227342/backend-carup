package com.nero.carupapi.model;

import jakarta.persistence.*;

@Entity
@Table(name="moviles")
public class Movil {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idMovil;
    private String nombre;
    private String matricula;
    private Byte esCamion;

    public Integer getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(Integer idMovil) {
        this.idMovil = idMovil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Byte getEsCamion() {
        return esCamion;
    }

    public void setEsCamion(Byte esCamion) {
        this.esCamion = esCamion;
    }

}
