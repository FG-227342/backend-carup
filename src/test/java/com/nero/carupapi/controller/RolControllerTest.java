package com.nero.carupapi.controller;

import com.nero.carupapi.model.Convenio;
import com.nero.carupapi.model.Rol;
import com.nero.carupapi.repository.RolRepository;
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

@WebMvcTest(value = RolController.class)
public class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RolRepository rolRepo;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private JWTService jwtService;

    Optional<Rol> mockRol;
    Rol rolPrueba;

    @BeforeEach
    void setUp() {

        rolPrueba = new Rol();
        rolPrueba.setId(1L);
        rolPrueba.setNombre("Rol 1");
        rolPrueba.setDescripcion("descripcion");
        mockRol = Optional.of(rolPrueba);

        List<Rol> a = new ArrayList<>();
        a.add(rolPrueba);
        when(rolRepo.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarTodos() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/roles")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"nombre\":\"Rol 1\",\"descripcion\":\"descripcion\",\"id\":1}]");
    }
}
