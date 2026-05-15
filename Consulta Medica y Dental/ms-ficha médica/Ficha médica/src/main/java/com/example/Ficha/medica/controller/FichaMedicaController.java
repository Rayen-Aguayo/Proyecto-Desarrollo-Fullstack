package com.example.Ficha.medica.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.Ficha.medica.dto.ApiResponse;
import com.example.Ficha.medica.dto.FichaMedicaDTO;
import com.example.Ficha.medica.dto.FichaMedicaResponse;
import com.example.Ficha.medica.model.FichaMedica;
import com.example.Ficha.medica.service.FichaMedicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/fichas_medicas")
public class FichaMedicaController {

    @Autowired
    private FichaMedicaService fichaMedicaService; // Quitamos el final para que el @Autowired funcione simple

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<FichaMedica>> crear(@Valid @RequestBody FichaMedicaDTO dto,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(
                ApiResponse.<FichaMedica>builder()
                        .success(true)
                        .message("Ficha Medica creada")
                        .data(fichaMedicaService.crear(dto, token))
                        .build()
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<FichaMedica>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.<List<FichaMedica>>builder()
                        .success(true)
                        .message("Listado obtenido")
                        .data(fichaMedicaService.listar())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<FichaMedicaResponse>> obtener(@PathVariable Long id, 
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(
                ApiResponse.<FichaMedicaResponse>builder()
                        .success(true)
                        .data(fichaMedicaService.obtener(id, token)) // minúscula: fichaMedicaService
                        .build()
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<FichaMedicaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FichaMedicaDTO dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                ApiResponse.<FichaMedicaResponse>builder()
                        .success(true)
                        .message("Se cambió la hora")
                        .data(fichaMedicaService.actualizar(id, dto, token)) // minúscula: fichaMedicaService
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        fichaMedicaService.eliminar(id); // Corregido el nombre del servicio duplicado
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Se anuló la hora")
                        .build()
        );
    }
}