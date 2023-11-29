package com.nero.carupapi.controller;

import com.nero.carupapi.dto.TurnoDTO;
import com.nero.carupapi.model.Turno;
import com.nero.carupapi.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService turnoSrv;

    public TurnoController(TurnoService turnoSrv){
        this.turnoSrv = turnoSrv;
    };

    @GetMapping
    public List<Turno> getTurno() {
        return turnoSrv.findAll();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Turno> altaTurno(@RequestBody Turno turno) {
        Turno t = turnoSrv.save(turno);
        return new ResponseEntity<>( t, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Turno obtenerTurnoPorId(@PathVariable Integer id) {
        return turnoSrv.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Turno actualizarTurno(@PathVariable Integer id, @RequestBody Turno nuevoTurno) {
        Turno turnoActual = turnoSrv.findById(id).orElse(null);
        if (turnoActual != null) {
            turnoActual.setIdMovil(nuevoTurno.getIdMovil());
            turnoActual.setIdChofer(nuevoTurno.getIdChofer());
            return turnoSrv.save(turnoActual);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Integer id) {
        turnoSrv.deleteById(id);
    }

    @GetMapping("/dto")
    public List<TurnoDTO> getTurnosDTO() {
        return turnoSrv.getAllDto();
    }
}
