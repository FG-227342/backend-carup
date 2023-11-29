package com.nero.carupapi.controller;

import com.nero.carupapi.model.Afiliacion;
import com.nero.carupapi.repository.AfiliacionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    private final AfiliacionRepository afiliaRepo;

    public AfiliacionController(AfiliacionRepository afiliaRepo) {
        this.afiliaRepo = afiliaRepo;
    }

    @GetMapping
    public List<Afiliacion> getAfiliaciones() {
        return afiliaRepo.findAll();
    }
}
