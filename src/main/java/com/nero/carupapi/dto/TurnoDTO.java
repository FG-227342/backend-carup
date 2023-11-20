package com.nero.carupapi.dto;

import java.time.LocalDateTime;

public class TurnoDTO {

    private Short idMovil;
    private Short idChofer;
    private LocalDateTime fechaHora;

    private String nombreMovil;

    public String getNombreMovil() {
        return nombreMovil;
    }

    public void setNombreMovil(String nombreMovil) {
        this.nombreMovil = nombreMovil;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    private String nombreChofer;

    public TurnoDTO() {
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
