package com.nero.carupapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nero.carupapi.model.Cliente;
import com.nero.carupapi.model.TipoCliente;
import com.nero.carupapi.repository.TipoClienteRepository;
import com.nero.carupapi.security.jwt.JWTService;
import com.nero.carupapi.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(value = ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private TipoClienteRepository tipoRepo;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private JWTService jwtService;

    Optional<Cliente> mockCli;
    Cliente cliPrueba;

    @BeforeEach
    void setUp() {

        cliPrueba = new Cliente("Federico", "3.417.146-7", "Direccion Prueba 1", "095478451", "123541", 1, null, null, "set up test");
        cliPrueba.setIdCliente(2L);
        LocalDate myDate = LocalDate.of(2023, 10, 04);
        mockCli = Optional.of(cliPrueba);

        List<TipoCliente> a = new ArrayList<>();
        when(tipoRepo.findAll()).thenReturn(a);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void listarClientes() throws Exception {
        ArrayList<Cliente> list = new ArrayList<Cliente>();
        Cliente b = new Cliente("User Test", "3.417.146-7", "Direccion Prueba 1", "095478451", "123541", 1, null, null, "Created by Test");
        list.add(b);
        when(clienteService.listarClientes()).thenReturn(list);
        MvcResult result = mockMvc.perform(get("/api/clientes")
                .contentType(APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println("CONTENIDO:");
        System.out.println(content);
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void obtenerCliente() throws Exception {
        when(clienteService.obtenerCliente(2L)).thenReturn(mockCli);

        // Realizar una solicitud GET al endpoint del controlador
        mockMvc.perform(get("/api/clientes/2")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.idCliente").value(2))
                .andExpect(jsonPath("$.documento").value("3.417.146-7"))
                .andExpect(jsonPath("$.direccion").value("Direccion Prueba 1"))
                .andExpect(jsonPath("$.telefono").value("095478451"));
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    public void testClienteNotFound() throws Exception {
        // Simular que el servicio devuelve null para un usuario inexistente
        Optional<Cliente> op = Optional.empty();
        when(clienteService.obtenerCliente(1L)).thenReturn(op);

        // Realizar una solicitud GET al endpoint del controlador
        mockMvc.perform(get("/api/clientes/1").with(csrf())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void crearCliente() throws Exception {

        cliPrueba = new Cliente("User Test", "3.417.146-7", "Direccion Prueba 1", "095478451", "123541", 1, 1, 1, "Created by Test");

        when(clienteService.crearCliente(any(Cliente.class))).thenReturn(cliPrueba);

        //  .content("{\"nombre\": \"Federico\",\"documento\": \"3.417.146-7\",\"direccion\": \"Direccion Prueba 1\",\"telefono\": \"095478451\",\"celular\": \"123541\",\"fechaAlta\": \"2023-10-04\",\"idTipoCliente\": 1,\"idAfiliacion\": null,\"idConvenio\": null,\"alta\": true,\"notas\": \"set up test\"}"))
        mockMvc.perform(post("/api/clientes").with(csrf())
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content("{\"documento\": \"3.417.146-7\",\"direccion\": \"Direccion Prueba 1\",\"telefono\": \"095478451\",\"celular\": \"123541\"}").characterEncoding("utf-8"))

                // .content(asJsonString(cliPrueba)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                // .andExpect(jsonPath("$.nombre").value("User Test"))
                .andExpect(jsonPath("$.documento").value("3.417.146-7"))
                .andExpect(jsonPath("$.direccion").value("Direccion Prueba 1"))
                .andExpect(jsonPath("$.telefono").value("095478451"))
                .andExpect(jsonPath("$.celular").value("123541"))
                .andExpect(status().isCreated()).andDo(print());
    }


    @Test
    @WithMockUser(username = "fede", password = "1234")
    void actualizarCliente() throws Exception {
        when(clienteService.actualizarCliente(eq(1L),any(Cliente.class))).thenReturn(cliPrueba);
        mockMvc.perform(put("/api/clientes/1").with(csrf())
                        .accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON)
                        .content("{\"documento\": \"3.417.146-7\",\"direccion\": \"Direccion Prueba 1\",\"telefono\": \"095478451\",\"celular\": \"123541\"}").characterEncoding("utf-8"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @WithMockUser(username = "fede", password = "1234")
    void eliminarCliente() throws Exception {
        when(clienteService.existe(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/clientes/1").with(csrf())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper ob = new ObjectMapper();
            ob.registerModule(new JavaTimeModule());
            return ob.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}