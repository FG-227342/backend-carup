package com.nero.carupapi.repository;

import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.model.UsuariosMobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuariosMobileRepository extends JpaRepository<UsuariosMobile,Long> {

    Optional<UsuariosMobile> findByNombre(String nombre);

    @Query("SELECT u FROM UsuariosMobile u WHERE u.prestador.idPrestador = :id")
    Optional<UsuariosMobile> findByIdPrestador(@Param("id") Integer id);


    @Query("SELECT u FROM UsuariosMobile u WHERE u.chofer.idChofer = :id")
    Optional<UsuariosMobile> findByIdChofer(@Param("id") Integer id);
}
