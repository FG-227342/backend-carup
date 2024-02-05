package com.nero.carupapi.utils;

import com.nero.carupapi.dto.ServicioMobileDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServicioMobileDTOConverter {

    private final ModelMapper modelMapper;
    private final PaisRepository paisRepo;
    private final CiudadRepository ciudadRepo;
    private final LocalidadRepository localidadRepo;
    private final VehiculoRepository vehiculoRepo;
    private final MarcaRepository marcaRepo;
    private final FallaRepository fallaRepo;

    public ServicioMobileDTOConverter(ModelMapper modelMapper, PaisRepository paisRepo, CiudadRepository ciudadRepo, LocalidadRepository localidadRepo, ZonaRepository zonaRepo, VehiculoRepository vehiculoRepo, MarcaRepository marcaRepo, FallaRepository fallaRepo) {
        this.modelMapper = modelMapper;
        this.paisRepo = paisRepo;
        this.ciudadRepo = ciudadRepo;
        this.localidadRepo = localidadRepo;
        this.vehiculoRepo = vehiculoRepo;
        this.marcaRepo = marcaRepo;
        this.fallaRepo = fallaRepo;
    }

    public ServicioMobileDTO convertServicioMobileDTO(Servicio s){

        Optional<Pais> paisOrigen = paisRepo.findById(s.getPaisOrigen());
        Optional<Localidad> locOrigen = localidadRepo.findById(s.getLocOrigen());
        Optional<Ciudad> ciudadOrigen = ciudadRepo.findById(s.getCiudadOrigen());
        Optional<Vehiculo> vehiculo = vehiculoRepo.findById(s.getIdVehiculo() != null ? s.getIdVehiculo() : 0);
        Optional<Marca> marca = marcaRepo.findById(vehiculo.get().getIdMarca());
        Optional<Falla> falla = fallaRepo.findById(Integer.valueOf(s.getIdFalla()));

        ServicioMobileDTO srvDTO = modelMapper.map(s, ServicioMobileDTO.class);
        srvDTO.setPaisOrigen(paisOrigen.get().getNombre());
        srvDTO.setLocOrigen(locOrigen.get().getNombre());
        srvDTO.setCiudadOrigen(ciudadOrigen.get().getNombre());
        srvDTO.setMatricula(vehiculo.get().getMatricula());
        srvDTO.setMarca(marca.get().getNombre());
        srvDTO.setModelo(vehiculo.get().getModelo());
        srvDTO.setColor(vehiculo.get().getColor());
        srvDTO.setFalla(falla.get().getNombre());

        return srvDTO;
    }
}
