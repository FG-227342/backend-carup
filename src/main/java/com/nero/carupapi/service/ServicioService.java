package com.nero.carupapi.service;

import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final UsuarioRepository userRepo;
    private final ServicioRepository servRepo;

    private final PaisRepository paisRepo;

    private final CiudadRepository ciudadRepo;

    private final LocalidadRepository localidadRepo;

    private final ZonaRepository zonaRepo;

    private final ClienteService cliSrv;

    private final FallaRepository fallaRepo;

    private final VehiculoRepository vehiculoRepo;

    private final MarcaRepository marcaRepo;

    public ServicioService(UsuarioRepository userRepo, ServicioRepository servRepo, PaisRepository paisRepo, CiudadRepository ciudadRepo, LocalidadRepository localidadRepo, ZonaRepository zonaRepo, ClienteService cliSrv, FallaRepository fallaRepo, VehiculoRepository vehiculoRepo, MarcaRepository marcaRepo) {
        this.userRepo = userRepo;
        this.servRepo = servRepo;
        this.paisRepo = paisRepo;
        this.ciudadRepo = ciudadRepo;
        this.localidadRepo = localidadRepo;
        this.zonaRepo = zonaRepo;
        this.cliSrv = cliSrv;
        this.fallaRepo = fallaRepo;
        this.vehiculoRepo = vehiculoRepo;
        this.marcaRepo = marcaRepo;
    }

    //ABM básicos
    public Servicio save(Servicio srv){
        return servRepo.save(srv);
    }

    public List<Servicio> findAll(){
        return servRepo.findAll();
    }

    public Optional<Servicio> buscarPorId(Long id){
        return servRepo.findById(id);
    }

    public void deleteById(Long Id){
        servRepo.deleteById(Id);
    }

    public Servicio actualizarSrv(Long id, Servicio servicio) {
        if (!servRepo.existsById(id)) {
            return null;
        }
        servicio.setIdSrv(id);
        servicio = servRepo.save(servicio);
        return servicio;
    }

    // NEGOCIO
    public Short getIdTarea(Long IdServicio){

        Optional<Servicio> srv = servRepo.findById(IdServicio);
        if(srv.isPresent()){
            return srv.get().getIdTarea();
        }
        return 0;
    }



    public List<ServicioWebDTO> getAllWebDto(){
        List<Servicio> turnos = servRepo.findAll();

        List<ServicioWebDTO> result = new ArrayList<>();

        turnos.forEach(s -> {

            Optional<Usuario> usuario = userRepo.findById(s.getIdUsuario());
            Optional<Pais> paisOrigen = paisRepo.findById(s.getPaisOrigen());
            Optional<Localidad> locOrigen = localidadRepo.findById(s.getLocOrigen());
            Optional<Localidad> locDestino = localidadRepo.findById(s.getLocDestino() != null ? s.getLocDestino() :0);
            Optional<Ciudad> ciudadOrigen = ciudadRepo.findById(s.getCiudadOrigen());
            Optional<Ciudad> ciudadDestino = ciudadRepo.findById(s.getCiudadDestino() != null ? s.getCiudadDestino() : 0);
            Optional<Zona> zona = zonaRepo.findById(  Integer.valueOf(s.getZona()));
            Optional<Cliente> cliente = cliSrv.obtenerCliente(s.getClienteId());
            Optional<Falla> falla = fallaRepo.findById(Integer.valueOf(s.getIdFalla()));
            Optional<Vehiculo> vehiculo = vehiculoRepo.findById(s.getIdVehiculo());
            Optional<Marca> marca = marcaRepo.findById(vehiculo.get().getIdMarca());

            ServicioWebDTO nuevo = new ServicioWebDTO();
                nuevo.setIdSrv(s.getIdSrv());
                nuevo.setIdTarea(s.getIdTarea());
                nuevo.setFecha(s.getFecha());
                nuevo.setHora(s.getHora());
                nuevo.setUsuario(usuario.get().getNombre());
                nuevo.setPaisOrigen(paisOrigen.get().getNombre());
                nuevo.setLocOrigen(locOrigen.get().getNombre());
                nuevo.setCiudadOrigen(ciudadOrigen.get().getNombre());
                nuevo.setZona(zona.get().getNombre());
                nuevo.setNombreCliente(cliente.get().getNombre());
                nuevo.setCalleOrigen(s.getCalleOrigen());
                nuevo.setNumPuertaOrigen(s.getNumPuertaOrigen());
                nuevo.setEsquinaOrigen(s.getEsquinaOrigen());
                nuevo.setFalla(falla.get().getNombre());
                nuevo.setMatricula(vehiculo.get().getMatricula());
                nuevo.setMarca(marca.get().getNombre());
                nuevo.setModelo(vehiculo.get().getModelo());
                nuevo.setColor(vehiculo.get().getColor());
                nuevo.setEstado(s.getEstado());
                locDestino.ifPresent(localidad -> nuevo.setLocDestino(localidad.getNombre()));
                ciudadDestino.ifPresent(ciudad -> nuevo.setCiudadDestino(ciudad.getNombre()));
                nuevo.setCalleDestino(s.getCalleDestino());
                nuevo.setObservaciones(s.getObservaciones());
                    result.add(nuevo);


        });
        return result;
    }

    public Optional<Servicio> modificarEstado(Long IdServicio, String estado){
        Optional<Servicio> srv = servRepo.findById(IdServicio);
        if(srv.isPresent()){
            srv.get().setEstado(estado.toUpperCase());
            servRepo.save(srv.get());
        }
        return srv;
    }

    public Optional<Servicio> asignarServicio(Long IdServicio, Integer idMovil, Integer idPrestador){
        Optional<Servicio> srv = servRepo.findById(IdServicio);
        if(srv.isPresent()){
            srv.get().setEstado("A");
            // si viene un movil de la empresa
            if(idMovil != null){
               srv.get().setIdMovil(idMovil);
               srv.get().setIdPrestador(null);
            } if(idPrestador != null){
                srv.get().setIdPrestador(idPrestador);
                srv.get().setIdMovil(null);
             }
            servRepo.save(srv.get());
        }
        return srv;
    }

}
