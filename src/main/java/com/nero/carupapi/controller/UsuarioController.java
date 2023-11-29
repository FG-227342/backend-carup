package com.nero.carupapi.controller;

import com.nero.carupapi.model.Rol;
import com.nero.carupapi.model.Usuario;
import com.nero.carupapi.repository.RolRepository;
import com.nero.carupapi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository userRepo;
    private final RolRepository rolRepo;

    public UsuarioController(UsuarioRepository userRepo, RolRepository rolRepo) {
        this.userRepo = userRepo;
        this.rolRepo = rolRepo;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return userRepo.obtenerUsuariosConRoles();
    }

    @PostMapping(produces="application/json")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {

        Optional<Rol> opt = rolRepo.findById(usuario.getIdRol());
        usuario.setRol(opt.get());
        Usuario u = userRepo.save(usuario);
        return new ResponseEntity<>( u, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        userRepo.deleteById(id);
    }
}
