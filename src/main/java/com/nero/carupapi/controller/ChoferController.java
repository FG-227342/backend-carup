package com.nero.carupapi.controller;

import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.repository.ChoferRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/choferes")
public class ChoferController {
    private final ChoferRepository chofRepo;

    public ChoferController(ChoferRepository chofRepo) {
        this.chofRepo = chofRepo;
    }

    @GetMapping
    public List<Chofer> getChoferes() {
        return chofRepo.findAll();
    }
}
