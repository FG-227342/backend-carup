package com.nero.carupapi.controller;

import com.nero.carupapi.dto.VehiculoDTO;
import com.nero.carupapi.model.Vehiculo;
import com.nero.carupapi.service.VehiculoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Vehiculo> crearVrhiculo(@RequestBody Vehiculo vhc) {
        try{
            Vehiculo v = vehService.save(vhc);
            return new ResponseEntity<>( v, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            Vehiculo v = vehService.vehiculoPorMatriculayCliente(vhc.getMatricula(),vhc.getIdCliente());
            return new ResponseEntity<>( v, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(  HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizaVehiculo(@PathVariable Long id, @RequestBody Vehiculo veh) {
        Vehiculo v  = vehService.actualizarVehiculo(id, veh);
        if(v == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(v);
    }

    @GetMapping("/todosPorCliente/{idCliente}")
    public List<VehiculoDTO> todosPorIdCliente(@PathVariable Long idCliente) {
        return vehService.todosPorIdCLiente(idCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerDTO(@PathVariable Long id) {
        VehiculoDTO res  = vehService.obtenerVehiculoDTO(id);
        if(res.getIdVehiculo() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
}
