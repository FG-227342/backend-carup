package com.nero.carupapi.controller;

import com.nero.carupapi.model.Marca;
import com.nero.carupapi.repository.MarcaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    private final MarcaRepository marcaRepo;

    public MarcaController(MarcaRepository marcaRepo) {
        this.marcaRepo = marcaRepo;
    }

    @GetMapping
    public List<Marca> getFallas() {
        return marcaRepo.findAll();
    }
}
