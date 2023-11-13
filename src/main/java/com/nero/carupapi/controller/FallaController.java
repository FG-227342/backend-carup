package com.nero.carupapi.controller;

import com.nero.carupapi.model.Falla;
import com.nero.carupapi.repository.FallaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/fallas")
public class FallaController {

    private final FallaRepository fallaRepo;


    public FallaController(FallaRepository fallaRepo) {
        this.fallaRepo = fallaRepo;
    }

    @GetMapping
    public List<Falla> getFallas() {
        return fallaRepo.findAll();
    }
}
