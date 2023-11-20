package com.nero.carupapi.repository;

import com.nero.carupapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol")
    List<Usuario> obtenerUsuariosConRoles();

    Optional<Usuario> findByEmail(String email);
}
