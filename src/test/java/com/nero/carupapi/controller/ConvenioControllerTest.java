package com.nero.carupapi.controller;

import com.nero.carupapi.model.Convenio;
import com.nero.carupapi.model.Prestador;
import com.nero.carupapi.repository.ConvenioRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(value = ConvenioController.class)
public class ConvenioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConvenioRepository convRepo;


    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private JWTService jwtService;

    Optional<Convenio> mockConvenio;
    Convenio convPrueba;
    @BeforeEach
    void setUp() {

        convPrueba = new Convenio();
        convPrueba.setIdConvenio(1);
        convPrueba.setNombre("convenio 1");
        mockConvenio = Optional.of(convPrueba);

        List<Convenio> a = new ArrayList<>();
        a.add(convPrueba);
        when(convRepo.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarTodos() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/convenios")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content,"[{\"idConvenio\":1,\"nombre\":\"convenio 1\"}]");
    }

}
