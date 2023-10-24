package com.nero.carupapi.controller;

import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.repository.ChoferRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Chofer getChofer(@PathVariable Integer id) {
        return chofRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Chofer crearChofer(@RequestBody Chofer chof) {
        return chofRepo.save(chof);
    }
}
