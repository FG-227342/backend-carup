package com.nero.carupapi.repository;

import com.nero.carupapi.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais,Short> {
}
