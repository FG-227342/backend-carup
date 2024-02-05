package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="servicios")
public class Servicio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idSrv;
    private Short idTarea;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer idUsuario;
    private Short paisOrigen;
    private Integer ciudadOrigen;
    private Integer locOrigen;
    private Short zona;
    private Long clienteId;
    private Long idVehiculo;
    private Integer idMovil;
    private Integer idPrestador;
    private String latitud;
    private String longitud;
    private String calleOrigen;
    private String numPuertaOrigen;
    private String esquinaOrigen;
    private Short idFalla;
    private String calleDestino;
    private Integer ciudadDestino;
    private Integer locDestino;
    private String observaciones;
    private String estado;
    private Byte llegadaLugar;
    private String solicitante;
    private String celSolicitante;
    private LocalTime horaAsignado;

    public Servicio() {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.estado = "P";
    }

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

    public LocalDate getFecha() {
        return fecha;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Short getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Short paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Integer getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Integer ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Integer getLocOrigen() {
        return locOrigen;
    }

    public void setLocOrigen(Integer locOrigen) {
        this.locOrigen = locOrigen;
    }

    public Short getZona() {
        return zona;
    }

    public void setZona(Short zona) {
        this.zona = zona;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public Short getIdFalla() {
        return idFalla;
    }

    public void setIdFalla(Short idFalla) {
        this.idFalla = idFalla;
    }

    public String getCalleDestino() {
        return calleDestino;
    }

    public void setCalleDestino(String calleDestino) {
        this.calleDestino = calleDestino;
    }

    public Integer getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Integer ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Integer getLocDestino() {
        return locDestino;
    }

    public void setLocDestino(Integer locDestino) {
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

    public LocalTime getHoraAsignado() {
        return horaAsignado;
    }

    public void setHoraAsignado(LocalTime horaAsignado) {
        this.horaAsignado = horaAsignado;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCelSolicitante() {
        return celSolicitante;
    }

    public void setCelSolicitante(String celSolicitante) {
        this.celSolicitante = celSolicitante;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "idSrv=" + idSrv +
                ", idTarea=" + idTarea +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", idUsuario=" + idUsuario +
                ", paisOrigen=" + paisOrigen +
                ", ciudadOrigen=" + ciudadOrigen +
                ", locOrigen=" + locOrigen +
                ", zona=" + zona +
                ", clienteId=" + clienteId +
                ", idVehiculo=" + idVehiculo +
                ", idMovil=" + idMovil +
                ", idPrestador=" + idPrestador +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", calleOrigen='" + calleOrigen + '\'' +
                ", numPuertaOrigen='" + numPuertaOrigen + '\'' +
                ", esquinaOrigen='" + esquinaOrigen + '\'' +
                ", idFalla=" + idFalla +
                ", calleDestino='" + calleDestino + '\'' +
                ", ciudadDestino=" + ciudadDestino +
                ", locDestino=" + locDestino +
                ", observaciones='" + observaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", llegadaLugar=" + llegadaLugar +
                '}';
    }
}
