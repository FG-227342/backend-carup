package com.nero.carupapi.controller;

import com.nero.carupapi.model.Marca;
import com.nero.carupapi.repository.MarcaRepository;
import com.nero.carupapi.security.jwt.JWTService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@WebMvcTest(value=MarcaController.class)
public class MarcaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarcaRepository marcaRepo;

    @MockBean
    private JWTService jwtService;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    static List<Marca> marcas = new ArrayList<Marca>();

    @BeforeAll
    public static void setUp() {
        Marca m1 = new Marca();
        m1.setIdMarca(1);
        m1.setNombre("Vokswagen");
        Marca m2 = new Marca();
        m2.setIdMarca(2);
        m2.setNombre("Fiat");
        marcas.add(m1);
        marcas.add(m2);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void getMarcas() throws Exception {
        when(marcaRepo.findAll()).thenReturn(marcas);

        mockMvc.perform(get("/api/marcas")
                .contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$[0].idMarca").value("1"));
    }
}
