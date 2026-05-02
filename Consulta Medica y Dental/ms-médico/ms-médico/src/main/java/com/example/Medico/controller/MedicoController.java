package com.example.Medico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.Medico.model.Medico;
import com.example.Medico.service.MedicoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("api/v1/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedico() {
        List<Medico> medicos = medicoService.findAll();
        if(medicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> guardar(@Valid @RequestBody Medico medico) {
        Medico medico 
    }
}
