package com.nero.carupapi.service;

import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.repository.ClienteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository cRepo;

    public ClienteService(ClienteRepository cRepo) {
        this.cRepo = cRepo;
    }

    public boolean existe(Long id) {
        return cRepo.existsById(id);
    }

    public List<Cliente> listarClientes() {
        return cRepo.findAll();
    }

    public Optional<Cliente> obtenerCliente(Long id) {
        return cRepo.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return cRepo.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        if (!cRepo.existsById(id)) {
            return null;
        }
        cliente.setIdCliente(id);
        cliente = cRepo.save(cliente);
        return cliente;
    }

    public void eliminarCliente(Long id) {
        cRepo.deleteById(id);
    }

    public List<Cliente> buscarPorTexto(String texto, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return cRepo.buscarPorTexto(texto, pageable);
    }
}
