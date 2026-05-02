package com.example.Medico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.Medico.model.Medico;
import com.example.Medico.service.MedicoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("api/v1/medicos")
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
        Medico medicoNuevo = medicoService.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoNuevo);
    }

    @GetMapping("/id")
    public ResponseEntity<Medico> buscar(@PathVariable String id) {
        try {
            Medico medico = medicoService.findById(id);
            return ResponseEntity.ok(medico);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizar(@PathVariable String id, @Valid @RequestBody Medico medico) {
        try {
            Medico med = medicoService.findById(id);
            med.setNombre(id);
            med.setRun(medico.getRun());
            med.setEdad(medico.getEdad());
            med.setTelefono(medico.getTelefono());
            med.setEspecialidad(medico.getEspecialidad());
            medicoService.save(med);
            return ResponseEntity.ok(medico);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        try {
            medicoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
