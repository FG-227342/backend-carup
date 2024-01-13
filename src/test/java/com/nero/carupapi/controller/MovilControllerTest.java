package com.nero.carupapi.controller;

import com.nero.carupapi.model.Movil;
import com.nero.carupapi.repository.MovilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@Profile("test")
@ExtendWith(MockitoExtension.class)
public class MovilControllerTest {
    @InjectMocks
    private MovilController controller;
    @Mock
    private MovilRepository movRepo;

    private Movil movPrueba;

    @BeforeEach
    void setUp() {
        movPrueba = new Movil("Móvil 157", "STX5022", (byte) 0);
        movPrueba.setIdMovil(1);
    }

    @Test
    public void getMoviles() {
        ArrayList<Movil> responseRepo = new ArrayList<>();
        responseRepo.add(movPrueba);
        when(movRepo.findAll()).thenReturn(responseRepo);

        List<Movil> response = controller.getMoviles();
        System.out.println(response.get(0));
        // Verifica que la lista no sea nula
        assertNotNull(response);
        // Verifica que cada objeto en la lista sea válido
        for (Movil obj : response) {
            assertEquals(obj.getClass(), Movil.class);
        }
    }
    @Test
    public void getMovilById(){
        when(movRepo.findById(1)).thenReturn(Optional.ofNullable(movPrueba));
        Movil response = controller.obtenerMovilPorId(1);
        assertEquals(response.getIdMovil(), movPrueba.getIdMovil());
    }

    @Test
    public void getMovilByIdNull(){
        assertNull(controller.obtenerMovilPorId(1));
    }
/*
    @Test
    public void actualizar(){
        when(movRepo.findById(1)).thenReturn(Optional.ofNullable(movPrueba));


        Movil MovilNuevo = new Movil("nuevo", "STX5022", (byte) 1);
        MovilNuevo.setIdMovil(1);

        when(movRepo.save(MovilNuevo)).thenReturn(MovilNuevo);

        Movil movilActual = controller.actualizarMovil(1,MovilNuevo);
        System.out.println("Actual" + movilActual);
        System.out.println(MovilNuevo);

    }
*/
}
