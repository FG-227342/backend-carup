package com.nero.carupapi.service;

import com.nero.carupapi.dto.ClienteWebDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.AfiliacionRepository;
import com.nero.carupapi.repository.ClienteRepository;
import com.nero.carupapi.repository.ConvenioRepository;
import com.nero.carupapi.repository.TipoClienteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository cRepo;
    private final TipoClienteRepository tipoCliRepo;
    private final ConvenioRepository convRepo;

    private final AfiliacionRepository afiliaRepo;

    public ClienteService(ClienteRepository cRepo, TipoClienteRepository tipoCliRepo, ConvenioRepository convRepo, AfiliacionRepository afiliaRepo) {
        this.cRepo = cRepo;
        this.tipoCliRepo = tipoCliRepo;
        this.convRepo = convRepo;
        this.afiliaRepo = afiliaRepo;
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

    // busqueda por nombre
    public List<Cliente> buscarPorTexto(String texto, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return cRepo.buscarPorTexto(texto, pageable);
    }

    // buscar directos por documento, tipoCliente 1 es Directo
    public Cliente buscarDirecto(String documento) {
        return cRepo.findByDocumentoAndIdTipoCliente(documento,1);
    }

    public List<ClienteWebDTO> buscarTodosDTO(){
        List<Cliente> clientes = cRepo.findAll();

        List<ClienteWebDTO> result = new ArrayList<>();

        clientes.forEach( c -> {
            Optional<TipoCliente> tipoCliente = tipoCliRepo.findById(c.getIdTipoCliente());
            Optional<Convenio> convenio = convRepo.findById(c.getIdConvenio() != null ? c.getIdConvenio():0);
            Optional<Afiliacion> afiliacion = afiliaRepo.findById(c.getIdAfiliacion() != null ? c.getIdAfiliacion():0);

            ClienteWebDTO nuevo = new ClienteWebDTO();
            nuevo.setIdCliente(c.getIdCliente());
            nuevo.setNombre(c.getNombre());
            nuevo.setDocumento(c.getDocumento());
            nuevo.setTelefono(c.getTelefono());
            nuevo.setCelular(c.getCelular());
            nuevo.setDireccion(c.getDireccion());
            nuevo.setFechaAlta(c.getFechaAlta());
            nuevo.setAlta(c.isAlta());
            nuevo.setNotas(c.getNotas());
            nuevo.setNombreTipoCliente(tipoCliente.get().getNombre());
            if(convenio.isPresent()){
            nuevo.setNombreConvenio(convenio.get().getNombre());
            }
            if(afiliacion.isPresent()){
            nuevo.setNombreAfiliacion(afiliacion.get().getNombre());
            }
            result.add(nuevo);
        });
        return result;
    }
}
