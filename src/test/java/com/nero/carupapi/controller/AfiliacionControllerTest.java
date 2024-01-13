package com.nero.carupapi.controller;

import com.nero.carupapi.model.Afiliacion;
import com.nero.carupapi.model.Convenio;
import com.nero.carupapi.repository.AfiliacionRepository;
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
@WebMvcTest(value = AfiliacionController.class)
public class AfiliacionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AfiliacionRepository afiliaRepo;


    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private JWTService jwtService;

    Optional<Afiliacion> mockAfilia;
    Afiliacion afiliaPrueba;
    @BeforeEach
    void setUp() {

        afiliaPrueba = new Afiliacion();
        afiliaPrueba.setIdAfiliacion(1);
        afiliaPrueba.setNombre("afiliacion 1");
        afiliaPrueba.setCosto((short) 0);
        afiliaPrueba.setSocioPersona((byte) 0);
        mockAfilia = Optional.of(afiliaPrueba);

        List<Afiliacion> a = new ArrayList<>();
        a.add(afiliaPrueba);
        when(afiliaRepo.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarTodos() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/afiliaciones")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idAfiliacion\":1,\"nombre\":\"afiliacion 1\",\"descripcion\":null,\"costo\":0,\"socioPersona\":0}]");
    }
}
