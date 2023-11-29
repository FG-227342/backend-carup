package com.nero.carupapi.controller;

import com.nero.carupapi.model.Prestador;
import com.nero.carupapi.repository.PrestadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestadores")
public class PrestadorController {

    private final PrestadorRepository repoPrestadores;

    public PrestadorController(PrestadorRepository repoPrestadores) {
        this.repoPrestadores = repoPrestadores;
    }

    @GetMapping
    public List<Prestador> gerPrestador() {
        return repoPrestadores.findAll();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Prestador> crearPrestador(@RequestBody Prestador presta) {
        Prestador p = repoPrestadores.save(presta);
        return new ResponseEntity<>( p, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Prestador obtenerPrestadorPorId(@PathVariable Integer id) {
        return repoPrestadores.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Prestador actualizarPrestador(@PathVariable Integer id, @RequestBody Prestador nuevoPrestador) {
        Prestador actual = repoPrestadores.findById(id).orElse(null);
        if (actual != null) {
            actual.setNombre(nuevoPrestador.getNombre());
            actual.setDescripcion(nuevoPrestador.getDescripcion());
            return repoPrestadores.save(actual);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestador(@PathVariable Integer id) {
        repoPrestadores.deleteById(id);
    }
}
