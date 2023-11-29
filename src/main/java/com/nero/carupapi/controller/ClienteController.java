package com.nero.carupapi.controller;

import com.nero.carupapi.dto.ClienteVehiculosDTO;
import com.nero.carupapi.dto.ClienteWebDTO;
import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.model.TipoCliente;
import com.nero.carupapi.repository.TipoClienteRepository;
import com.nero.carupapi.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/todosDTO")
    public List<ClienteWebDTO> listarClientesDTO() {
        return cliService.buscarTodosDTO();
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

    @GetMapping("/porNombre/{nombre}")
    public List<Cliente> getCliPorNombre(@PathVariable String nombre) {
        return cliService.buscarPorTexto(nombre,20);
    }

    @GetMapping("/buscarDirecto/{documento}")
    public ResponseEntity<Cliente> buscarDirecto(@PathVariable String documento) {
        Cliente res = cliService.buscarDirecto(documento);
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/clientesPorMatricula/{matricula}")
    public List<Cliente> clientesPorMatricula(@PathVariable String matricula) {
        return cliService.buscarPorMatricula(matricula);
    }

    // encuentro el cliente BSE correspondiente a la matrícula con sus vehiculos correspondientes
    @GetMapping("/clienteBsePorMatricula/{matricula}")
    public ResponseEntity<ClienteVehiculosDTO> clienteBsePorMatricula(@PathVariable String matricula) {
        ClienteVehiculosDTO cli = cliService.buscarPorMatriculaBSE(matricula);
        if(cli != null){
            return new ResponseEntity<>( cli, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // encuentro el cliente CARUP correspondiente a la cedula + los vehículos
    @GetMapping("/clienteCARUP/{documento}")
    public ResponseEntity<ClienteVehiculosDTO> clienteCarupPorCi(@PathVariable String documento) {
        ClienteVehiculosDTO cli = cliService.buscarPorCiCARUP(documento);
        if(cli != null){
            return new ResponseEntity<>( cli, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
