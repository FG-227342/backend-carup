package com.nero.carupapi.controller;

import com.nero.carupapi.model.Prestador;
import com.nero.carupapi.repository.PrestadorRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = PrestadorController.class)
public class PrestadorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrestadorRepository prestadorRepository;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private JWTService jwtService;

    Optional<Prestador> mockPrestador;
    Prestador prestadorPrueba;
    @BeforeEach
    void setUp() {

        prestadorPrueba = new Prestador("Prestador 1","descripcion 1");
        prestadorPrueba.setIdPrestador((short)1);
        mockPrestador = Optional.of(prestadorPrueba);

        List<Prestador> a = new ArrayList<>();
        a.add(prestadorPrueba);
        when(prestadorRepository.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarTodos() throws Exception {

        MvcResult result = mockMvc.perform(get("/api/prestadores")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idPrestador\":1,\"nombre\":\"Prestador 1\",\"descripcion\":\"descripcion 1\"}]");
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void obtenerPorId() throws Exception {
        when(prestadorRepository.findById(1)).thenReturn(mockPrestador);

        // Realizar una solicitud GET al endpoint del controlador
        mockMvc.perform(get("/api/prestadores/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.idPrestador").value(1))
                .andExpect(jsonPath("$.descripcion").value("descripcion 1"))
                .andExpect(jsonPath("$.nombre").value("Prestador 1"));
    }
    @Test
    @WithMockUser(username = "fede", password = "1234")
    void crear() throws Exception{
        when(prestadorRepository.save(any(Prestador.class))).thenReturn(prestadorPrueba);

        mockMvc.perform(post("/api/prestadores").with(csrf())
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"Prestador 1\",\"descripcion\":\"descripcion 1\"}").characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.idPrestador").value(1))
                .andExpect(jsonPath("$.nombre").value("Prestador 1"))
                .andExpect(jsonPath("$.descripcion").value("descripcion 1"))
                .andDo(print());
    }

}
