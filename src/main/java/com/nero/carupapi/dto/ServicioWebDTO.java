package com.nero.carupapi.dto;

import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServicioWebDTO {
    @Id
    private Long idSrv;
    private Short idTarea;
    private LocalDate fecha;
    private LocalTime hora;
    private String usuario;
    private String paisOrigen;
    private String ciudadOrigen;
    private String locOrigen;
    private String zona;
    private String nombreCliente;
    private Long idVehiculo;
    private String matricula;

    private String marca;

    private String modelo;
    private String color;
    private Integer idMovil;
    private Integer idPrestador;
    private String latitud;
    private String longitud;
    private String calleOrigen;
    private String numPuertaOrigen;
    private String esquinaOrigen;
    private String falla;
    private String calleDestino;
    private String ciudadDestino;
    private String locDestino;
    private String observaciones;
    private String estado;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(Integer idMovil) {
        this.idMovil = idMovil;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
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

    public String getCalleDestino() {
        return calleDestino;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getLocDestino() {
        return locDestino;
    }

    public void setLocDestino(String locDestino) {
        this.locDestino = locDestino;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Byte getLlegadaLugar() {
        return llegadaLugar;
    }

    public void setLlegadaLugar(Byte llegadaLugar) {
        this.llegadaLugar = llegadaLugar;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "ServicioWebDTO{" +
                "idSrv=" + idSrv +
                ", idTarea=" + idTarea +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", usuario='" + usuario + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                ", locOrigen='" + locOrigen + '\'' +
                ", zona='" + zona + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", idVehiculo=" + idVehiculo +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", idMovil=" + idMovil +
                ", idPrestador=" + idPrestador +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", calleOrigen='" + calleOrigen + '\'' +
                ", numPuertaOrigen='" + numPuertaOrigen + '\'' +
                ", esquinaOrigen='" + esquinaOrigen + '\'' +
                ", falla='" + falla + '\'' +
                ", calleDestino='" + calleDestino + '\'' +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", locDestino='" + locDestino + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", llegadaLugar=" + llegadaLugar +
                '}';
    }
}
