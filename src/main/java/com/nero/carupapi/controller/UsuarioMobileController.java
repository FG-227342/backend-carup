package com.nero.carupapi.controller;

import com.nero.carupapi.model.UsuariosMobile;
import com.nero.carupapi.repository.UsuariosMobileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariosMobile")
public class UsuarioMobileController {

    private final UsuariosMobileRepository usrRepo;
    private final PasswordEncoder pe;

    public UsuarioMobileController(UsuariosMobileRepository usrRepo, PasswordEncoder pe) {
        this.usrRepo = usrRepo;
        this.pe = pe;
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

    @PostMapping(produces="application/json")
    public UsuariosMobile crearUserMobile(@RequestBody UsuariosMobile usuarioM) {
        String psw = pe.encode(usuarioM.getClave());
        usuarioM.setClave(psw);
        return usrRepo.save(usuarioM);
    }
}
