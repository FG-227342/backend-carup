package com.nero.carupapi.controller;

import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.model.Rol;
import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.repository.ChoferRepository;
import com.nero.carupapi.security.jwt.JWTService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value=ChoferController.class)
public class ChoferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChoferRepository chofRepo;

    @MockBean
    private JWTService jwtService;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    static List<Chofer> choferes = new ArrayList<Chofer>();

    static Chofer chofPrueba;

    @BeforeAll
    public static void setUp() {
        Chofer c1 = new Chofer();
        c1.setIdChofer(1);
        c1.setNombre("Diego");
        chofPrueba = c1;
        Chofer c2 = new Chofer();
        c2.setIdChofer(2);
        c2.setNombre("Gaston");
        choferes.add(c1);
        choferes.add(c2);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void getMarcas() throws Exception {
        when(chofRepo.findAll()).thenReturn(choferes);

        mockMvc.perform(get("/api/choferes")
                .contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$[0].idChofer").value("1"))
                .andExpect(jsonPath("$[0].nombre").value("Diego"))
                .andExpect(jsonPath("$[1].idChofer").value("2"))
                .andExpect(jsonPath("$[1].nombre").value("Gaston"));
    }

    void getChofer(){}
    @Test
    @WithMockUser(username = "fede", password = "1234")
    void crear() throws Exception {

        when(chofRepo.save(any(Chofer.class))).thenReturn(chofPrueba);
        mockMvc.perform(post("/api/choferes").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Diego\",\"celular\": null}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Diego"))
                .andExpect(jsonPath("$.idChofer").value(1));
    }

    void actualizar(){}
}
