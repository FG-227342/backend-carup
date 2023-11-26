package com.nero.carupapi.repository;

import com.nero.carupapi.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {

    List<Vehiculo> findAllByIdCliente(Long tipoCliente);
}
