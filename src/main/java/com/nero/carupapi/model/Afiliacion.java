package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="afiliaciones")
public class Afiliacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idAfiliacion;
    private String nombre;
    private String descripcion;
    private short costo;
    private byte socioPersona;
    private Byte idAgo;

    public Integer getIdAfiliacion() {
        return idAfiliacion;
    }

    public void setIdAfiliacion(Integer idAfiliacion) {
        this.idAfiliacion = idAfiliacion;
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

    public short getCosto() {
        return costo;
    }

    public void setCosto(short costo) {
        this.costo = costo;
    }

    public byte getSocioPersona() {
        return socioPersona;
    }

    public void setSocioPersona(byte socioPersona) {
        this.socioPersona = socioPersona;
    }
    @JsonIgnore
    public Byte getIdAgo() {
        return idAgo;
    }

    public void setIdAgo(Byte idAgo) {
        this.idAgo = idAgo;
    }

}
