package com.nero.carupapi.repository;

import com.nero.carupapi.model.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {
}
