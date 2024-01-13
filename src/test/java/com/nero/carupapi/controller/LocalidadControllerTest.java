package com.nero.carupapi.controller;

import com.nero.carupapi.model.*;
import com.nero.carupapi.repository.CiudadRepository;
import com.nero.carupapi.repository.LocalidadRepository;
import com.nero.carupapi.repository.PaisRepository;
import com.nero.carupapi.repository.ZonaRepository;
import com.nero.carupapi.security.jwt.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(value = LocalidadController.class)
public class LocalidadControllerTest {
    @MockBean
    private PaisRepository paisRepo;
    @MockBean
    private CiudadRepository ciudadRepo;
    @MockBean
    private LocalidadRepository localidadRepo;
    @MockBean
    private ZonaRepository zonaRepo;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @MockBean
    private JWTService jwtService;

    Pais paisPrueba = new Pais();
    Ciudad ciudadPrueba = new Ciudad();
    Localidad locPrueba = new Localidad();
    Zona zonaPrueba = new Zona();

    @BeforeEach
    void setUp() {

        paisPrueba.setIdPais((short) 1);
        paisPrueba.setNombre("Uruguay");
        ciudadPrueba.setIdCiudad(1);
        ciudadPrueba.setNombre("Montevideo");
        ciudadPrueba.setIdPais(1);
        locPrueba.setIdLocalidad(1);
        locPrueba.setNombre("Montevideo");
        locPrueba.setIdCiudad(1);
        zonaPrueba.setIdZona(1);
        zonaPrueba.setNombre("Parque Batlle");
        zonaPrueba.setIdLocalidad(1);

        List<Pais> paises = new ArrayList<>();
        List<Ciudad> ciudades = new ArrayList<>();
        List<Localidad> localidades = new ArrayList<>();
        List<Zona> zonas = new ArrayList<>();

        paises.add(paisPrueba);
        ciudades.add(ciudadPrueba);
        localidades.add(locPrueba);
        zonas.add(zonaPrueba);
        when(paisRepo.findAll()).thenReturn(paises);
        when(ciudadRepo.findAll()).thenReturn(ciudades);
        when(zonaRepo.findAll()).thenReturn(zonas);
        when(localidadRepo.findAll()).thenReturn(localidades);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarPaises() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/localidades/paises")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idPais\":1,\"nombre\":\"Uruguay\"}]");
    }
    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarLocalidades() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/localidades/localidades")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idLocalidad\":1,\"nombre\":\"Montevideo\",\"idCiudad\":1}]");
    }
    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarCiudades() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/localidades/ciudades")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idCiudad\":1,\"nombre\":\"Montevideo\",\"idPais\":1}]");
    }
    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarZonas() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/localidades/zonas")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idZona\":1,\"nombre\":\"Parque Batlle\",\"idLocalidad\":1}]");
    }

}
