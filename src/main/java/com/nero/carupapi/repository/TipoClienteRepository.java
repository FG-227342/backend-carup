package com.nero.carupapi.repository;

import com.nero.carupapi.model.TipoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoCliente,Integer> {
}
