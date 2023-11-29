package com.nero.carupapi.dto;
import java.time.LocalDate;
import java.util.List;

public class ClienteVehiculosDTO {
    private Long idCliente;
    private String nombre;
    private String documento;
    private String direccion;
    private String telefono;
    private String celular;
    private LocalDate fechaAlta;
    private Integer idTipoCliente;
    private Integer idAfiliacion;
    private Integer idConvenio;
    private boolean alta;
    private String notas;

    private List<VehiculoDTO> vehiculos;

    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public ClienteVehiculosDTO() {
        this.alta = true;
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
}
