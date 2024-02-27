package com.nero.carupapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServicioMobileDTO {

    private Long idSrv;
    private Short idTarea;
    private LocalDate fecha;
    private LocalTime hora;
    private String paisOrigen;
    private String ciudadOrigen;
    private String locOrigen;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private String calleOrigen;
    private String numPuertaOrigen;
    private String esquinaOrigen;
    private String falla;
    private String estado;
    private LocalTime horaAsignado;

    private Byte llegadaLugar;

    public Long getIdSrv() {
        return idSrv;
    }

    public void setIdSrv(Long idSrv) {
        this.idSrv = idSrv;
    }

    public Short getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Short idTarea) {
        this.idTarea = idTarea;
    }

    public String getFecha() {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fecha.format(pattern);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getLocOrigen() {
        return locOrigen;
    }

    public void setLocOrigen(String locOrigen) {
        this.locOrigen = locOrigen;
    }


    public String getCalleOrigen() {
        return calleOrigen;
    }

    public void setCalleOrigen(String calleOrigen) {
        this.calleOrigen = calleOrigen;
    }

    public String getNumPuertaOrigen() {
        return numPuertaOrigen;
    }

    public void setNumPuertaOrigen(String numPuertaOrigen) {
        this.numPuertaOrigen = numPuertaOrigen;
    }

    public String getEsquinaOrigen() {
        return esquinaOrigen;
    }

    public void setEsquinaOrigen(String esquinaOrigen) {
        this.esquinaOrigen = esquinaOrigen;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String idFalla) {
        this.falla = idFalla;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalTime getHoraAsignado() {
        return horaAsignado;
    }

    public void setHoraAsignado(LocalTime horaAsignado) {
        this.horaAsignado = horaAsignado;
    }

    public Byte getLlegadaLugar() {
        return llegadaLugar;
    }

    public void setLlegadaLugar(Byte llegadaLugar) {
        this.llegadaLugar = llegadaLugar;
    }

    @Override
    public String toString() {
        return "ServicioMobileDTO{" +
                "idSrv=" + idSrv +
                ", idTarea=" + idTarea +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", locOrigen='" + locOrigen + '\'' +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", calleOrigen='" + calleOrigen + '\'' +
                ", numPuertaOrigen='" + numPuertaOrigen + '\'' +
                ", esquinaOrigen='" + esquinaOrigen + '\'' +
                ", falla='" + falla + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
