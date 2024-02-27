package com.nero.carupapi.controller;

import com.nero.carupapi.dto.ServicioMobileDTO;
import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.model.Servicio;
import com.nero.carupapi.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/todosWebDTO/rangoFechas")
    public ResponseEntity<List<ServicioWebDTO>> obtenerTodosWebDTOFiltrado(@RequestBody Map<String, String> data) {

        if (data.containsKey("fechaInicial") && data.containsKey("fechaFinal")) {
            LocalDate fechaIni = LocalDate.parse(data.get("fechaInicial"));
            LocalDate fechaFin = LocalDate.parse(data.get("fechaFinal"));
            List<ServicioWebDTO> res = srvService.getAllWebDtoBetwenDates(fechaIni,fechaFin);
            return new ResponseEntity<List<ServicioWebDTO>>(res,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/servicioDTO/{id}")
    public ResponseEntity<ServicioWebDTO> obtenerSrvDTO(@PathVariable Long id) {
        ServicioWebDTO s = srvService.getOneDTO(id);
        if(s == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(s);
    }

@PatchMapping("/{id}")
public ResponseEntity<Servicio> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> estado) {

    if (estado.containsKey("estado")) {
        srvService.modificarEstado(id, estado.get("estado"));
        return new ResponseEntity<>(HttpStatus.OK);
    } else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @Transactional
    @PatchMapping("/asignarMovil/{id}")
    public ResponseEntity<Servicio> asignar(@PathVariable Long id, @RequestBody Map<String, Integer> data){
        Optional<Servicio> buscado;

        if (data.containsKey("idChofer") || data.containsKey("idPrestador")) {
            buscado = srvService.asignarServicio(id, data.get("idChofer"), data.get("idPrestador"));
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return buscado.map(servicio -> new ResponseEntity<>(servicio, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PatchMapping("/desasignar/{SrvId}")
    public ResponseEntity<String> desasignarServicio(@PathVariable Long SrvId){
        boolean res = srvService.desasignarServicio(SrvId);
        if(res){
            return new ResponseEntity<>("Servicio desasignado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el servicio", HttpStatus.NOT_FOUND);
        }
    }
    /*Solo los servicios pendientes o Aceptados*/
    @GetMapping("/usuarioMobile/{id}")
    public ResponseEntity<List<ServicioMobileDTO>> serviciosPorUsuarioMovil(@PathVariable Long id) {
        List<ServicioMobileDTO> res = srvService.obtenerPendientesPorIdMovil(id);
        if(res !=null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/usuarioMobile/todos/{id}")
    public ResponseEntity<List<ServicioMobileDTO>> todosServiciosPorUsuarioMovil(@PathVariable Long id) {
        List<ServicioMobileDTO> res = srvService.obtenerTodossPorIdMovil(id);
        if(res !=null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // si no viene la "fecha" toma la fecha actual por defecto
    /* Body esperado:
    {
    "idTarea":2,
    "fecha": "2024-02-03"
        }
     */
    @PostMapping("/buscarPorTarea")
    public ResponseEntity<Servicio> buscarPorTarea(@RequestBody Map<String, String> data) {
        Servicio buscado;
        LocalDate fecha;
        if(data.get("fecha") == null){
            fecha = LocalDate.now();
        } else {
            fecha =LocalDate.parse(data.get("fecha"));
        }
        System.out.println(fecha);
        if (data.containsKey("idTarea")) {

            buscado = srvService.buscarSrvPorTarea(Short.valueOf(data.get("idTarea")),fecha);
            if(buscado != null){
                return new ResponseEntity<>(buscado,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/llegadaLugar/{SrvId}")
    public void llegadaAlLugar(@PathVariable Long SrvId){
        srvService.llegadaAlLugar(SrvId);
    }
}
