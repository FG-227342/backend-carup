package com.nero.carupapi.controller;

import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.model.Servicio;
import com.nero.carupapi.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService srvService;

    public ServicioController(ServicioService srvService) {
        this.srvService = srvService;
    }

    @GetMapping
    public List<Servicio> obtenerTodos() {
        return srvService.findAll();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Servicio> nuevoServicio(@RequestBody Servicio srv) {
        Servicio s = srvService.save(srv);
        return new ResponseEntity<>( s, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Servicio obtenerSrvPorId(@PathVariable Integer id) {
        return srvService.buscarPorId((long)id).orElse(null);
    }

    // ID de la tarea por id de servicio
    @GetMapping("/idTarea/{id}")
    public Short obtenerIdTarea(@PathVariable Long id) {
        return srvService.getIdTarea(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio srv) {
        Servicio s  = srvService.actualizarSrv(id, srv);
        if(s == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(s);
    }

    @GetMapping("/todosWebDTO")
    public List<ServicioWebDTO> obtenerTodosWebDTO() {
        return srvService.getAllWebDto();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Servicio> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> estado) {
        Optional<Servicio> buscado;

        if (estado.containsKey("estado")) {
            buscado = srvService.modificarEstado(id, estado.get("estado"));
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(buscado.isPresent()){
            return new ResponseEntity<>(buscado.get(), HttpStatus.OK);
        }

     else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    @PatchMapping("/asignarMovil/{id}")
    public ResponseEntity<Servicio> asignar(@PathVariable Long id, @RequestBody Map<String, Integer> data) {
        Optional<Servicio> buscado;

        if (data.containsKey("idMovil") || data.containsKey("idPrestador")) {
            buscado = srvService.asignarServicio(id, data.get("idMovil"), data.get("idPrestador"));
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(buscado.isPresent()){
            return new ResponseEntity<>(buscado.get(), HttpStatus.OK);
        }

        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
