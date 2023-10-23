package com.nero.carupapi.repository;

import com.nero.carupapi.model.Movil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovilRepository extends JpaRepository<Movil, Integer> {
}
