package com.nero.carupapi.controller;

import com.nero.carupapi.model.Movil;
import com.nero.carupapi.repository.MovilRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/moviles")
public class MovilesController {

    private final MovilRepository movRepo;

    public MovilesController(MovilRepository movRepo) {
        this.movRepo = movRepo;
    }


    @GetMapping
    public List<Movil> getMoviles() {
        return movRepo.findAll();
    }
}
