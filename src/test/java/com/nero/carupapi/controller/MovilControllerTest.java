package com.nero.carupapi.controller;
/*
import com.nero.carupapi.model.Movil;
import com.nero.carupapi.repository.MovilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MovilControllerTest {
    @InjectMocks
    private MovilController controller;
    @Mock
    private MovilRepository movRepo;

    private Movil movPrueba;

    @BeforeEach
    void setUp() {
        movPrueba = new Movil("Móvil 157","STX5022", (byte) 0);
        movPrueba.setIdMovil(1);
    }

    @Test
    public void getMoviles(){
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
    }/*
    @Test
    public void getMovilById(){
        when(movRepo.getReferenceById(1)).thenReturn(movPrueba);

        Movil response = controller.obtenerMovilPorId(1);
       assertEquals(response.getIdMovil(), movPrueba.getIdMovil());

    }}
*/
