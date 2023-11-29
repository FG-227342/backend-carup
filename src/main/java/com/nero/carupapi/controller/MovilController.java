package com.nero.carupapi.controller;

import com.nero.carupapi.model.Movil;
import com.nero.carupapi.repository.MovilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moviles")
public class MovilController {

    private final MovilRepository movRepo;

    public MovilController(MovilRepository movRepo) {
        this.movRepo = movRepo;
    }


    @GetMapping
    public List<Movil> getMoviles() {
        return movRepo.findAll();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Movil> crearMovil(@RequestBody Movil movil) {
        Movil m = movRepo.save(movil);
        return new ResponseEntity<>( m, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Movil obtenerMovilPorId(@PathVariable Integer id) {
        return movRepo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Movil actualizarMovil(@PathVariable Integer id, @RequestBody Movil nuevoMovil) {
        Movil movilActual = movRepo.findById(id).orElse(null);
        if (movilActual != null) {
            movilActual.setNombre(nuevoMovil.getNombre());
            movilActual.setMatricula(nuevoMovil.getMatricula());
            movilActual.setEsCamion(nuevoMovil.getEsCamion());
            return movRepo.save(movilActual);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarMovil(@PathVariable Integer id) {
        movRepo.deleteById(id);
    }
}
