package com.nero.carupapi.repository;

import com.nero.carupapi.model.Rol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class RolRepositoryTest {
    @Mock
    private RolRepository rolRepo;
/*
    @Test
    public void crear(){
        Rol esperado = new Rol("TEST ROL","DESCRIPCIÓN");
        esperado.setId(1L);
        Mockito.when(rolRepo.save(new Rol("TEST ROL","DESCRIPCIÓN"))).thenReturn(esperado);
        Rol r = rolRepo.save(new Rol("TEST ROL","DESCRIPCIÓN"));
        Assertions.assertEquals(esperado.getId(),1L);
        Assertions.assertEquals(esperado.getNombre(), r.getNombre());
        Assertions.assertEquals(esperado.getDescripcion(), r.getDescripcion());

    }*/
}
