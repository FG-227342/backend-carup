package com.nero.carupapi.controller;

import com.nero.carupapi.model.Chofer;
import com.nero.carupapi.model.UsuariosMobile;
import com.nero.carupapi.repository.UsuariosMobileRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuariosMobile")
public class UsuarioMobileController {

    private final UsuariosMobileRepository usrRepo;

    public UsuarioMobileController(UsuariosMobileRepository usrRepo) {
        this.usrRepo = usrRepo;
    }

    @GetMapping
    public List<UsuariosMobile> getAll() {
        return usrRepo.findAll();
    }

    @GetMapping("/{id}")
    public UsuariosMobile getUsuario(@PathVariable Integer id) {
        return usrRepo.findById(id.longValue()).orElse(null);
    }

    // solo para pruebas
    @GetMapping("/porIdPrestador/{id}")
    public UsuariosMobile getUsuarioPorPrestador(@PathVariable Integer id) {
        return usrRepo.findByIdPrestador(id).orElse(null);
    }


}
