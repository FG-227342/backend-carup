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

    public Movil(String nombre, String matricula, Byte esCamion) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.esCamion = esCamion;
    }

    public Movil() {

    }

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

    @Override
    public String toString() {
        return "Movil{" +
                "idMovil=" + idMovil +
                ", nombre='" + nombre + '\'' +
                ", matricula='" + matricula + '\'' +
                ", esCamion=" + esCamion +
                '}';
    }
}
