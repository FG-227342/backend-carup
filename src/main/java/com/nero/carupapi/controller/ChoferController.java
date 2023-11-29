package com.nero.carupapi.controller;

import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.repository.ChoferRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Chofer crearChofer(@RequestBody Chofer chof) {
        return chofRepo.save(chof);
    }

    @PutMapping("/{id}")
    public Chofer actualizarChofer(@PathVariable Integer id, @RequestBody Chofer nuevoChofer) {
        Chofer choferActual = chofRepo.findById(id).orElse(null);
        if (choferActual != null) {
            choferActual.setNombre(nuevoChofer.getNombre());
            choferActual.setCelular(nuevoChofer.getCelular());
            return chofRepo.save(choferActual);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarChofer(@PathVariable Integer id) {
        chofRepo.deleteById(id);
    }
}
