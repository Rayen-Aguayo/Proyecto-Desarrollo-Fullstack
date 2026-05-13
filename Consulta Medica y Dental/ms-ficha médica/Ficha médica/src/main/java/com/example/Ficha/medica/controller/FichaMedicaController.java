package com.example.Ficha.medica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ficha.medica.dto.ApiResponse;
import com.example.Ficha.medica.dto.FichaMedicaDTO;
import com.example.Ficha.medica.model.FichaMedica;
import com.example.Ficha.medica.service.FichaMedicaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/fichas_medicas")
@RequiredArgsConstructor
public class FichaMedicaController {
    @Autowired
    private FichaMedicaService fichaMedicaService;

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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<FichaMedica>>> listar(
            @RequestHeader("Authorization") String token) {
        
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
    public ResponseEntity<ApiResponse<FichaMedica
    


    
    
}
