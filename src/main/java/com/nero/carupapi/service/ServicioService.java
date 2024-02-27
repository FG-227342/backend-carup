package com.nero.carupapi.service;

import com.nero.carupapi.dto.ServicioMobileDTO;
import com.nero.carupapi.dto.ServicioWebDTO;
import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.*;
import com.nero.carupapi.utils.ServicioMobileDTOConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    private final UsuariosMobileRepository usrMobileRepo;
    private final RestTemplate restTemplate;
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

    private final ChoferRepository chofRepo;

    private final PrestadorRepository prestadorRepo;

    private final ServicioMobileDTOConverter mobileDTOConverter;

    public ServicioService(UsuariosMobileRepository usrMobileRepo, RestTemplateBuilder restTemplate, UsuarioRepository userRepo, ServicioRepository servRepo, PaisRepository paisRepo, CiudadRepository ciudadRepo, LocalidadRepository localidadRepo, ZonaRepository zonaRepo, ClienteService cliSrv, FallaRepository fallaRepo, VehiculoRepository vehiculoRepo, MarcaRepository marcaRepo, ChoferRepository chofRepo, PrestadorRepository prestadorRepo, ServicioMobileDTOConverter mobileDTOConverter) {
        this.usrMobileRepo = usrMobileRepo;
        this.restTemplate = restTemplate.build();
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
        this.chofRepo = chofRepo;
        this.prestadorRepo = prestadorRepo;
        this.mobileDTOConverter = mobileDTOConverter;
    }

    //ABM básicos
    public Servicio save(Servicio srv) {
        return servRepo.save(srv);
    }

    public List<Servicio> findAll() {
        return servRepo.findAll();
    }

    public Optional<Servicio> buscarPorId(Long id) {
        return servRepo.findById(id);
    }

    public void deleteById(Long Id) {
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
    public Short getIdTarea(Long IdServicio) {

        Optional<Servicio> srv = servRepo.findById(IdServicio);
        if (srv.isPresent()) {
            return srv.get().getIdTarea();
        }
        return 0;
    }

    public List<ServicioWebDTO> getAllWebDto() {
        List<Servicio> allServicios = servRepo.findAll();

        List<ServicioWebDTO> result = new ArrayList<>();

        allServicios.forEach(s -> {
            Optional<Usuario> usuario = userRepo.findById(s.getIdUsuario());
            Optional<Pais> paisOrigen = paisRepo.findById(s.getPaisOrigen());
            Optional<Localidad> locOrigen = localidadRepo.findById(s.getLocOrigen());
            Optional<Localidad> locDestino = localidadRepo.findById(s.getLocDestino() != null ? s.getLocDestino() : 0);
            Optional<Ciudad> ciudadOrigen = ciudadRepo.findById(s.getCiudadOrigen());
            Optional<Ciudad> ciudadDestino = ciudadRepo.findById(s.getCiudadDestino() != null ? s.getCiudadDestino() : 0);
            Optional<Zona> zona = zonaRepo.findById(Integer.valueOf(s.getZona()));
            Optional<Cliente> cliente = cliSrv.obtenerCliente(s.getClienteId());
            Optional<Falla> falla = fallaRepo.findById(Integer.valueOf(s.getIdFalla()));
            Optional<Vehiculo> vehiculo = vehiculoRepo.findById(s.getIdVehiculo() != null ? s.getIdVehiculo() : 0);
            Optional<Marca> marca = marcaRepo.findById(vehiculo.get().getIdMarca());
            Optional<Chofer> chofer = s.getIdMovil() != null ? chofRepo.findById(s.getIdMovil()) : Optional.empty();
            Optional<Prestador> prestador = s.getIdPrestador() != null ? prestadorRepo.findById(s.getIdPrestador()) : Optional.empty();

            ServicioWebDTO nuevo = new ServicioWebDTO();
            nuevo.setLatitud(s.getLatitud());
            nuevo.setLongitud(s.getLongitud());
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
            nuevo.setLlegadaLugar(s.getLlegadaLugar());
            nuevo.setSolicitante(s.getSolicitante());
            nuevo.setCelSolicitante(s.getCelSolicitante());
            nuevo.setIdMovil(s.getIdMovil());
            nuevo.setIdPrestador(s.getIdPrestador());
            locDestino.ifPresent(localidad -> nuevo.setLocDestino(localidad.getNombre()));
            ciudadDestino.ifPresent(ciudad -> nuevo.setCiudadDestino(ciudad.getNombre()));
            nuevo.setCalleDestino(s.getCalleDestino());
            nuevo.setObservaciones(s.getObservaciones());
            chofer.ifPresent(m -> nuevo.setNombreMovil(m.getNombre()));
            prestador.ifPresent(p -> nuevo.setNombrePrestador(p.getNombre()));
            result.add(nuevo);
        });
        return result;
    }

    /*
        public Optional<Servicio> modificarEstado(Long IdServicio, String estado) {
            Optional<Servicio> srv = servRepo.findById(IdServicio);
            if (srv.isPresent()) {
                srv.get().setEstado(estado.toUpperCase());
                servRepo.save(srv.get());
            }
            return srv;
        }
        */
    public void modificarEstado(Long idSrv, String estado) {
        servRepo.cambiarEstado(idSrv, estado);
    }

    @Transactional
    public Optional<Servicio> asignarServicio(Long IdServicio, Integer idChofer, Integer idPrestador) {
        Optional<Servicio> srv = servRepo.findById(IdServicio);
        Optional<UsuariosMobile> u = Optional.empty();
        Short idTarea = 0;
        if (srv.isPresent()) {

            // si viene un movil de la empresa
            if (idChofer != null) {
                srv.get().setIdMovil(idChofer);
                srv.get().setIdPrestador(null);
                //Busco el UsuarioMobile para poder obtener luego la playerId del dispositivo asociado
                u = usrMobileRepo.findByIdChofer(idChofer);
            }
            if (idPrestador != null) {
                u = usrMobileRepo.findByIdPrestador(idPrestador);
                srv.get().setIdPrestador(idPrestador);
                srv.get().setIdMovil(null);
            }
            if (u.isPresent()) {
                idTarea = srv.get().getIdTarea();
                String playerId = u.get().getPlayerId();
                enviarNotificacion(IdServicio, idTarea, playerId);
            }
            srv.get().setEstado("A");
            srv.get().setHoraAsignado(LocalTime.now());

            servRepo.save(srv.get());


        }
        return srv;
    }

    @Transactional
    public boolean desasignarServicio(Long IdServicio) {
        Optional<Servicio> srv = servRepo.findById(IdServicio);
        if (srv.isPresent()) {
            srv.get().setEstado("P");
            srv.get().setIdMovil(null);
            srv.get().setIdPrestador(null);
            servRepo.save(srv.get());
            return true;
        }
        return false;
    }

    //Envío de notificación al dispositivo desde One Signal, identificado por la playerId obenida en la BD
    private void enviarNotificacion(Long idServicio, Short IdTarea, String playerId) {
        String apiUrl = "https://onesignal.com/api/v1/notifications";
        String requestBody = """
                {
                  "app_id": "f727b6cc-4f46-4d91-ae78-1c7e86e27e5f",
                  "data": {"title": "Hola!", "idSrv":%s},
                "include_player_ids":["%s"],
                  "contents": {"en": "Se le ha asignado el servicio %s"}
                }
                """.formatted(idServicio, playerId, IdTarea);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }

    public ServicioWebDTO getOneDTO(Long idSrv) {
        Optional<Servicio> opt = servRepo.findById(idSrv);

        if (opt.isPresent()) {
            Servicio s = opt.get();

            Optional<Usuario> usuario = userRepo.findById(s.getIdUsuario());
            Optional<Pais> paisOrigen = paisRepo.findById(s.getPaisOrigen());
            Optional<Localidad> locOrigen = localidadRepo.findById(s.getLocOrigen());
            Optional<Localidad> locDestino = localidadRepo.findById(s.getLocDestino() != null ? s.getLocDestino() : 0);
            Optional<Ciudad> ciudadOrigen = ciudadRepo.findById(s.getCiudadOrigen());
            Optional<Ciudad> ciudadDestino = ciudadRepo.findById(s.getCiudadDestino() != null ? s.getCiudadDestino() : 0);
            Optional<Zona> zona = zonaRepo.findById(Integer.valueOf(s.getZona()));
            Optional<Cliente> cliente = cliSrv.obtenerCliente(s.getClienteId());
            Optional<Falla> falla = fallaRepo.findById(Integer.valueOf(s.getIdFalla()));
            Optional<Vehiculo> vehiculo = vehiculoRepo.findById(s.getIdVehiculo() != null ? s.getIdVehiculo() : 0);
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
            nuevo.setLatitud(s.getLatitud());
            nuevo.setLongitud(s.getLongitud());
            nuevo.setLlegadaLugar(s.getLlegadaLugar());
            locDestino.ifPresent(localidad -> nuevo.setLocDestino(localidad.getNombre()));
            ciudadDestino.ifPresent(ciudad -> nuevo.setCiudadDestino(ciudad.getNombre()));
            nuevo.setCalleDestino(s.getCalleDestino());
            nuevo.setObservaciones(s.getObservaciones());
            nuevo.setCelSolicitante(s.getCelSolicitante());
            return nuevo;
        }
        return null;
    }

    // Servicios asignados a un movil o prestador determinado
    public List<ServicioMobileDTO> obtenerPendientesPorIdMovil(Long idUsuario) {
        Optional<UsuariosMobile> usuario = usrMobileRepo.findById(idUsuario);
        List<Servicio> servicios = new ArrayList<>();

        if (usuario.isPresent()) {
            if (usuario.get().getChofer() == null) {
                servicios = servRepo.obtenerServiciosPorPrestador(usuario.get().getPrestador().getIdPrestador());
            } else {
                servicios = servRepo.obtenerServiciosPorMovil(usuario.get().getChofer().getIdChofer());
            }
            List<ServicioMobileDTO> serviciosMobile = new ArrayList<>();

            servicios.forEach(s -> {
                serviciosMobile.add(convertir(s));
            });
            return serviciosMobile;
        }
        return null;
    }

    public List<ServicioMobileDTO> obtenerTodossPorIdMovil(Long idUsuario) {
        Optional<UsuariosMobile> usuario = usrMobileRepo.findById(idUsuario);
        List<Servicio> servicios = new ArrayList<>();

        if (usuario.isPresent()) {
            if (usuario.get().getChofer() == null) {
                servicios = servRepo.obtenerTodosServiciosPorPrestador(usuario.get().getPrestador().getIdPrestador());
            } else {
                servicios = servRepo.obtenerTodosServiciosPorMovil(usuario.get().getChofer().getIdChofer());
            }
            List<ServicioMobileDTO> serviciosMobile = new ArrayList<>();

            servicios.forEach(s -> {
                serviciosMobile.add(convertir(s));
            });
            return serviciosMobile;
        }
        return null;
    }

    public Servicio buscarSrvPorTarea(Short idTarea, LocalDate fecha) {
        return servRepo.obtenerSrvPorTarea(idTarea, fecha);
    }

    public ServicioMobileDTO convertir(Servicio s) {
        return mobileDTOConverter.convertServicioMobileDTO(s);
    }


    public List<ServicioWebDTO> getAllWebDtoBetwenDates(LocalDate fechaIni, LocalDate fechaFin) {
        List<Servicio> allServicios = servRepo.obtenerTodosRangoFecha(fechaIni, fechaFin);

        List<ServicioWebDTO> result = new ArrayList<>();

        allServicios.forEach(s -> {
            Optional<Usuario> usuario = userRepo.findById(s.getIdUsuario());
            Optional<Pais> paisOrigen = paisRepo.findById(s.getPaisOrigen());
            Optional<Localidad> locOrigen = localidadRepo.findById(s.getLocOrigen());
            Optional<Localidad> locDestino = localidadRepo.findById(s.getLocDestino() != null ? s.getLocDestino() : 0);
            Optional<Ciudad> ciudadOrigen = ciudadRepo.findById(s.getCiudadOrigen());
            Optional<Ciudad> ciudadDestino = ciudadRepo.findById(s.getCiudadDestino() != null ? s.getCiudadDestino() : 0);
            Optional<Zona> zona = zonaRepo.findById(Integer.valueOf(s.getZona()));
            Optional<Cliente> cliente = cliSrv.obtenerCliente(s.getClienteId());
            Optional<Falla> falla = fallaRepo.findById(Integer.valueOf(s.getIdFalla()));
            Optional<Vehiculo> vehiculo = vehiculoRepo.findById(s.getIdVehiculo() != null ? s.getIdVehiculo() : 0);
            Optional<Marca> marca = marcaRepo.findById(vehiculo.get().getIdMarca());
            Optional<Chofer> chofer = s.getIdMovil() != null ? chofRepo.findById(s.getIdMovil()) : Optional.empty();
            Optional<Prestador> prestador = s.getIdPrestador() != null ? prestadorRepo.findById(s.getIdPrestador()) : Optional.empty();

            ServicioWebDTO nuevo = new ServicioWebDTO();
            nuevo.setLatitud(s.getLatitud());
            nuevo.setCliente(cliente.get());
            nuevo.setLongitud(s.getLongitud());
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
            nuevo.setLlegadaLugar(s.getLlegadaLugar());
            nuevo.setSolicitante(s.getSolicitante());
            nuevo.setCelSolicitante(s.getCelSolicitante());
            nuevo.setIdMovil(s.getIdMovil());
            nuevo.setIdPrestador(s.getIdPrestador());
            locDestino.ifPresent(localidad -> nuevo.setLocDestino(localidad.getNombre()));
            ciudadDestino.ifPresent(ciudad -> nuevo.setCiudadDestino(ciudad.getNombre()));
            nuevo.setCalleDestino(s.getCalleDestino());
            nuevo.setObservaciones(s.getObservaciones());
            chofer.ifPresent(m -> nuevo.setNombreMovil(m.getNombre()));
            prestador.ifPresent(p -> nuevo.setNombrePrestador(p.getNombre()));
            result.add(nuevo);
        });
        return result;
    }

    @Transactional
    public void llegadaAlLugar(Long idServicio) {
        Servicio s = servRepo.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + idServicio));
        s.setLlegadaLugar((byte) 1);
        servRepo.save(s);
    }
}
