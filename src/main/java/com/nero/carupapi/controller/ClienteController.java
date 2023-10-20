package com.nero.carupapi.controller;

import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.model.TipoCliente;
import com.nero.carupapi.repository.TipoClienteRepository;
import com.nero.carupapi.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService cliService;
    private final TipoClienteRepository tipoCliRepo;

    public ClienteController(ClienteService cliService, TipoClienteRepository tipoCliRepo) {
        this.cliService = cliService;
        this.tipoCliRepo = tipoCliRepo;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return cliService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = cliService.obtenerCliente(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente c = cliService.crearCliente(cliente);
        return new ResponseEntity<>( c, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente d  = cliService.actualizarCliente(id, cliente);
        if(d == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (!cliService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        cliService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipoClientes")
    public List<TipoCliente> getTipoClientes() {
        return tipoCliRepo.findAll();
    }
}
