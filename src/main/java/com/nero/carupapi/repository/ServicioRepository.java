package com.nero.carupapi.repository;

import com.nero.carupapi.model.Servicio;
import com.nero.carupapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT u FROM Servicio u WHERE u.idMovil = :id and u.estado IN ('A','X')")
    List<Servicio> obtenerServiciosPorMovil(@Param("id") Integer id);

    @Query("SELECT u FROM Servicio u WHERE u.idPrestador = :id and u.estado IN ('A','X')")
    List<Servicio> obtenerServiciosPorPrestador(@Param("id") Short id);

    @Query("SELECT s FROM Servicio s WHERE s.idTarea = :idTarea and s.fecha =:fecha")
    Servicio obtenerSrvPorTarea(@Param("idTarea") Short idTarea,@Param("fecha") LocalDate fecha);

    @Query("SELECT u FROM Servicio u WHERE u.idMovil = :id")
    List<Servicio> obtenerTodosServiciosPorMovil(@Param("id") Integer id);

    @Query("SELECT u FROM Servicio u WHERE u.idPrestador = :id")
    List<Servicio> obtenerTodosServiciosPorPrestador(@Param("id") Short id);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Query(value="UPDATE servicios s SET s.estado=:estado WHERE s.idSrv=:idSrv", nativeQuery = true)
    void cambiarEstado(@Param("idSrv") Long idSrv, @Param("estado") String estado);

    @Query(value="SELECT * FROM servicios s WHERE s.fecha BETWEEN :fechaIni AND :fechaFin", nativeQuery = true)
    List<Servicio> obtenerTodosRangoFecha(@Param("fechaIni") LocalDate fechaIni,@Param("fechaFin") LocalDate fechaFin);

}
