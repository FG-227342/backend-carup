package com.nero.carupapi.controller;

import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository userRepo;

    public UsuarioController(UsuarioRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return userRepo.findAll();
    }
}
