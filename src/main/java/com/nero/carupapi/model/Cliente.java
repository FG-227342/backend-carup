package com.nero.carupapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombre;
    private String documento;
    private String direccion;
    private String telefono;
    private String celular;
    @Column(insertable = false)
    private LocalDate fechaAlta;

    /*
    @OneToOne()
    @JoinColumn(name = "idtipocliente", referencedColumnName = "idtipo")
    private TipoCliente tipoCliente;
     */
    private Integer idTipoCliente;
    private Integer idAfiliacion;
    private Integer idConvenio;
    private boolean alta;
    private String notas;

    private String email;

    public Cliente() {
        this.alta = true;
    }

    public Cliente(String nombre, String documento, String direccion, String telefono, String celular, Integer idTipoCliente, Integer idAfiliacion, Integer idConvenio, String notas) {
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.idTipoCliente = idTipoCliente;
        this.idAfiliacion = idAfiliacion;
        this.idConvenio = idConvenio;
        this.alta = true;
        this.notas = notas;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
   //@JsonIgnore
    public LocalDate getFechaAlta() {

        if(fechaAlta == null)
            return LocalDate.now();
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(Integer idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public Integer getIdAfiliacion() {
        return idAfiliacion;
    }

    public void setIdAfiliacion(Integer idAfiliacion) {
        this.idAfiliacion = idAfiliacion;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
