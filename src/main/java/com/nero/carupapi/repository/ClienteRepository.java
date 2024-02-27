package com.nero.carupapi.repository;

import com.nero.carupapi.model.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("SELECT e FROM Cliente e WHERE e.nombre like %:texto%")
    List<Cliente> buscarPorTexto(@Param("texto") String texto, Pageable pageable);

    Cliente findByDocumentoAndIdTipoCliente(String documento, int tipoCliente);

    @Query("SELECT e FROM Cliente e WHERE e.nombre like %:texto%")
    List<Cliente> buscarPorTextoSinPaginar(@Param("texto") String texto);

}
