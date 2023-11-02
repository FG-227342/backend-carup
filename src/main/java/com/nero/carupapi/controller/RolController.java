package com.nero.carupapi.controller;

import com.nero.carupapi.model.Rol;
import com.nero.carupapi.repository.RolRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepo;

    public RolController(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }
    @GetMapping
    public List<Rol> getRoles() {
        return rolRepo.findAll();
    }

}
