package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.PacienteDTO;
import com.example.model.Paciente;
import com.example.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Paciente>> crear(@Valid @RequestBody APacienteDTO dto) {

        Paciente autor = pacienteService.crear(dto);

        return ResponseEntity.status(201).body(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Autor creado")
                        .data(autor)
                        .build()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<Paciente>>> listar() {

        return ResponseEntity.ok(
                ApiResponse.<List<Paciente>>builder()
                        .success(true)
                        .message("Listado obtenido")
                        .data(pacienteService.listar())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse<Paciente>> obtener(@PathVariable String id) {

        return ResponseEntity.ok(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Autor obtenido")
                        .data(pacienteService.obtener(id))
                        .build()
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Paciente>> actualizar(@PathVariable String id,
                                                        @Valid @RequestBody PacienteDTO dto) {

        Paciente paciente = pacienteService.actualizar(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("Autor actualizado")
                        .data(paciente)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable String id) {

        pacienteService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Autor eliminado")
                        .build()
        );
    }
}
