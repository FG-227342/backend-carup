package com.nero.carupapi.controller;

import com.nero.carupapi.model.Rol;
import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.repository.RolRepository;
import com.nero.carupapi.repository.UsuarioRepository;
import com.nero.carupapi.security.jwt.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository userRepo;
    @MockBean
    private RolRepository rolRepo;
    @MockBean
    private JWTService jwtService;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    //setup
    Usuario usrPrueba;
    List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    @BeforeEach
    void setUp() {
        usrPrueba = new Usuario("federico", "fg@prueba.com", "asd1234");
        listaUsuarios.add(new Usuario("usuario1", "u1@prueba.com", "prueba1"));
        listaUsuarios.add(new Usuario("usuario2", "u2@prueba.com", "prueba2"));
        listaUsuarios.get(0).setIdUsuario(1);
        listaUsuarios.get(1).setIdUsuario(2);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void obtenerUsuarios() throws Exception {
        when(userRepo.obtenerUsuariosConRoles()).thenReturn(listaUsuarios);
        mockMvc.perform(get("/api/usuarios")
                .contentType(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void crearUsuario() throws Exception {
        Rol r = new Rol("prueba", "rol de test");
        r.setId(1L);
        Optional<Rol> rol1 = Optional.of(r);
        usrPrueba.setRol(r);
        usrPrueba.setIdUsuario(1);

        when(rolRepo.findById(1)).thenReturn(rol1);
        when(userRepo.save(any(Usuario.class))).thenReturn(usrPrueba);
        mockMvc.perform(post("/api/usuarios").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"federico\", \"email\": \"fg@prueba.com\",\"clave\": \"1234\",\"idRol\": 1}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("federico"))
                .andExpect(jsonPath("$.email").value("fg@prueba.com"));
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void eliminarUsuario() throws Exception {

        MvcResult result = mockMvc.perform(delete("/api/usuarios/1").with(csrf())).andExpect(status().isOk()).andReturn();
    }
}
