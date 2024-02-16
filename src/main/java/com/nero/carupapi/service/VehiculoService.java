package com.nero.carupapi.service;

import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.dto.VehiculoDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.MarcaRepository;
import com.nero.carupapi.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehRepo;
    private final MarcaRepository marcaRepo;

    public VehiculoService(VehiculoRepository vehRepo, MarcaRepository marcaRepo) {
        this.vehRepo = vehRepo;
        this.marcaRepo = marcaRepo;
    }

    public Vehiculo save(Vehiculo veh){
        return vehRepo.save(veh);
    }

    public Vehiculo actualizarVehiculo(Long id, Vehiculo nuevo) {
        Vehiculo existente = vehRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El vehículo con id " + id + " no existe"));

        Optional.ofNullable(nuevo.getIdMarca()).ifPresent(existente::setIdMarca);
        Optional.ofNullable(nuevo.getMatricula()).ifPresent(existente::setMatricula);
        Optional.ofNullable(nuevo.getModelo()).ifPresent(existente::setModelo);
        Optional.ofNullable(nuevo.getColor()).ifPresent(existente::setColor);
        Optional.ofNullable(nuevo.getAño()).ifPresent(existente::setAño);
        Optional.ofNullable(nuevo.getIdCliente()).ifPresent(existente::setIdCliente);

        return vehRepo.save(existente);
    }

    public VehiculoDTO obtenerVehiculoDTO(Long id) {
        Optional<Vehiculo> res = vehRepo.findById(id);
        VehiculoDTO nuevo = new VehiculoDTO();
        if(res.isPresent()){
            Optional<Marca> marca = marcaRepo.findById(res.get().getIdMarca());
            nuevo.setIdVehiculo(res.get().getIdVehiculo());
            nuevo.setMatricula(res.get().getMatricula());
            nuevo.setIdMarca(res.get().getIdMarca());
            nuevo.setNombreMarca(marca.get().getNombre());
            nuevo.setModelo(res.get().getModelo());
            nuevo.setColor(res.get().getColor());
            nuevo.setAño(res.get().getAño());
            nuevo.setIdCliente(res.get().getIdCliente());
        }
        return nuevo;
    }

    public Optional<Vehiculo> obtenerVehiculo(Long id) {
        return vehRepo.findById(id);
    }

    public List<Vehiculo> findAll(){
        return vehRepo.findAll();
    }

    public List<VehiculoDTO> findAllDTO() {
        List<Vehiculo> vehiculos = vehRepo.findAll();

        List<VehiculoDTO> result = new ArrayList<>();

        vehiculos.forEach(v -> {

            Optional<Marca> marca = marcaRepo.findById(v.getIdMarca());

            VehiculoDTO nuevo = new VehiculoDTO();
            nuevo.setIdVehiculo(v.getIdVehiculo());
            nuevo.setMatricula(v.getMatricula());
            nuevo.setIdMarca(v.getIdMarca());
            nuevo.setNombreMarca(marca.get().getNombre());
            nuevo.setModelo(v.getModelo());
            nuevo.setColor(v.getColor());
            nuevo.setAño(v.getAño());
            nuevo.setIdCliente(v.getIdCliente());
            result.add(nuevo);
        });

        return result;
    }

   public  List<VehiculoDTO> todosPorIdCLiente(Long idCliente){
        List<Vehiculo> vehiculos = vehRepo.findAllByIdCliente(idCliente);

        List<VehiculoDTO> result = new ArrayList<>();

        vehiculos.forEach(v -> {

            Optional<Marca> marca = marcaRepo.findById(v.getIdMarca());

            VehiculoDTO nuevo = new VehiculoDTO();
            nuevo.setIdVehiculo(v.getIdVehiculo());
            nuevo.setMatricula(v.getMatricula());
            nuevo.setIdMarca(v.getIdMarca());
            nuevo.setNombreMarca(marca.get().getNombre());
            nuevo.setModelo(v.getModelo());
            nuevo.setColor(v.getColor());
            nuevo.setAño(v.getAño());
            nuevo.setIdCliente(v.getIdCliente());
            result.add(nuevo);
        });

        return result;
    }

    public List<Vehiculo> buscarPorMatricula(String matricula){
        return vehRepo.findByMatricula(matricula);
    }

    //retornar id cliente a partir de la matricula
    public List<Long> buscarClientes(String matricula){
        List<Vehiculo> vehiculos = vehRepo.findByMatricula(matricula);
        List<Long> idsClientes = new ArrayList<Long>();
        if(vehiculos != null){
            vehiculos.forEach(vehiculo -> {
                idsClientes.add(vehiculo.getIdCliente());
            });
        }
        return idsClientes;
    }


    public Vehiculo vehiculoPorMatriculayCliente(String matricula, Long idCliente){
        List<Vehiculo> vehiculos = vehRepo.findByMatricula(matricula);
        for (Vehiculo v : vehiculos) {
            if (v.getIdCliente().equals(idCliente)) {
                return v;
            }
        }
        return null;
    }
}
