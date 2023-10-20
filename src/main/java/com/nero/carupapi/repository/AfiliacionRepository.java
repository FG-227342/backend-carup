package com.nero.carupapi.repository;

import com.nero.carupapi.model.Afiliacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliacionRepository extends JpaRepository<Afiliacion, Integer> {
}
