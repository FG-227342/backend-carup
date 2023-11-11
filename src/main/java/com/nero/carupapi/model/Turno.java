package com.nero.carupapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="turnos")
public class Turno {
    @Id
    private Short idMovil;
    private Short idChofer;
    private LocalDateTime fechaHora;

    public Turno() {
        this.fechaHora = LocalDateTime.now();
    }

    public Short getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(Short idMovil) {
        this.idMovil = idMovil;
    }

    public Short getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Short idChofer) {
        this.idChofer = idChofer;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
