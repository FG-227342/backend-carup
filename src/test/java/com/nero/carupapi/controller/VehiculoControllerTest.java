package com.nero.carupapi.controller;

import com.nero.carupapi.model.Prestador;
import com.nero.carupapi.model.Vehiculo;
import com.nero.carupapi.repository.VehiculoRepository;
import com.nero.carupapi.security.jwt.JWTService;
import com.nero.carupapi.service.VehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = VehiculoController.class)
public class VehiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehiculoRepository vehRepo;
    @MockBean
    private VehiculoService vehService;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @MockBean
    private JWTService jwtService;

    Optional<Vehiculo> mockVehiculo;
    Vehiculo vehPrueba;

    @BeforeEach
    void setUp() {

        vehPrueba = new Vehiculo();
        vehPrueba.setIdVehiculo(1L);
        vehPrueba.setAño("2023");
        vehPrueba.setColor("Rojo");
        vehPrueba.setMatricula("stx1234");
        vehPrueba.setModelo("Palio");
        vehPrueba.setIdMarca(1);
        mockVehiculo = Optional.of(vehPrueba);

        List<Vehiculo> a = new ArrayList<>();
        a.add(vehPrueba);
        when(vehService.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarTodos() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/vehiculos")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertEquals(content,"[{\"idVehiculo\":1,\"matricula\":\"stx1234\",\"idMarca\":1,\"modelo\":\"Palio\",\"color\":\"Rojo\",\"año\":\"2023\",\"idCliente\":null}]");
    }

}
