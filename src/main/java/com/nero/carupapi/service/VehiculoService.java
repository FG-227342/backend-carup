package com.nero.carupapi.service;

import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.dto.VehiculoDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.MarcaRepository;
import com.nero.carupapi.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
