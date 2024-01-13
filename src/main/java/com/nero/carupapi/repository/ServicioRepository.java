package com.nero.carupapi.repository;

import com.nero.carupapi.model.Servicio;
import com.nero.carupapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT u FROM Servicio u WHERE u.idMovil = :id and u.estado IN ('A','X')")
    List<Servicio> obtenerServiciosPorMovil(@Param("id") Integer id);

    @Query("SELECT u FROM Servicio u WHERE u.idPrestador = :id and u.estado IN ('A','X')")
    List<Servicio> obtenerServiciosPorPrestador(@Param("id") Short id);
}
