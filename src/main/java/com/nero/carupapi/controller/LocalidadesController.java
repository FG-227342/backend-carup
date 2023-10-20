package com.nero.carupapi.controller;

import com.nero.carupapi.model.Ciudad;
import com.nero.carupapi.model.Localidad;
import com.nero.carupapi.model.Pais;
import com.nero.carupapi.model.Zona;
import com.nero.carupapi.repository.CiudadRepository;
import com.nero.carupapi.repository.LocalidadRepository;
import com.nero.carupapi.repository.PaisRepository;
import com.nero.carupapi.repository.ZonaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/localidades")
public class LocalidadesController {

    private final PaisRepository paisRepo;
    private final CiudadRepository ciudadRepo;
    private final LocalidadRepository localidadRepo;
    private final ZonaRepository zonaRepo;

    public LocalidadesController(PaisRepository paisRepository, CiudadRepository ciudadRepo, LocalidadRepository localidadRepo, ZonaRepository zonaRepo) {
        this.paisRepo = paisRepository;
        this.ciudadRepo = ciudadRepo;
        this.localidadRepo = localidadRepo;
        this.zonaRepo = zonaRepo;
    }

    @GetMapping("/paises")
    public List<Pais> getPaises() {
        return paisRepo.findAll();
    }

    @GetMapping("/ciudades")
    public List<Ciudad> getCiudades() {
        return ciudadRepo.findAll();
    }

    @GetMapping("/localidades")
    public List<Localidad> getLocalidades() {
        return localidadRepo.findAll();
    }

    @GetMapping("/zonas")
    public List<Zona> getZonas() {
        return zonaRepo.findAll();
    }
}
