package com.nero.carupapi.model;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "tipocliente")
public class TipoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short idTipo;
    private String nombre;
    private Short idAgo;

    @Override
    public String toString() {
        return "TIPO: " + nombre + " , id: " + idTipo;
    }
}
