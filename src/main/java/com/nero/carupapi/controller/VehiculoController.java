package com.nero.carupapi.controller;

import com.nero.carupapi.dto.VehiculoDTO;
import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.model.Vehiculo;
import com.nero.carupapi.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehService;

    public VehiculoController(VehiculoService vehService) {
        this.vehService = vehService;
    }

    @GetMapping
    public List<Vehiculo> getAll() {
        return vehService.findAll();
    }

    @GetMapping("/getAllDTO")
    public List<VehiculoDTO> getAllDTO() {
        return vehService.findAllDTO();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Vehiculo> crearMovil(@RequestBody Vehiculo vhc) {
        Vehiculo v = vehService.save(vhc);
        return new ResponseEntity<>( v, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizaVehiculo(@PathVariable Long id, @RequestBody Vehiculo veh) {
        Vehiculo v  = vehService.actualizarVehiculo(id, veh);
        if(v == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(v);
    }
}
