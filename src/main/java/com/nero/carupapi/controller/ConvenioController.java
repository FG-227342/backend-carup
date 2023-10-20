package com.nero.carupapi.controller;

import com.nero.carupapi.model.Convenio;
import com.nero.carupapi.repository.ConvenioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/convenios")
public class ConvenioController {

    private final ConvenioRepository convRepo;


    public ConvenioController(ConvenioRepository convRepo) {
        this.convRepo = convRepo;
    }

    @GetMapping
    public List<Convenio> getConvenios() {
        return convRepo.findAll();
    }
}
