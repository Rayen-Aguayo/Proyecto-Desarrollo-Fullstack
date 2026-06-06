package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.PacienteDTO;
import com.example.model.Paciente;
import com.example.service.PacienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pacientes", description = "Operaciones relacionadas con pacientes")

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @Operation(
        summary = "Creacion de pacientes",
        description = "Permite crear un nuevo paciente. Solo accesible para usuarios con rol ADMIN."
)
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Paciente creado exitosamente"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "No autenticado o token inválido"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Acceso denegado")
})

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Paciente>> crear(@Valid @RequestBody PacienteDTO dto) {

        Paciente paciente = pacienteService.crear(dto);

        return ResponseEntity.status(201).body(
                ApiResponse.<Paciente>builder()
                        .success(true)
                        .message("paciente creado")
                        .data(paciente)
                        .build()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
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
                        .message("paciente obtenido")
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
                        .message("paciente actualizado")
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
                        .message("paciente eliminado")
                        .build()
        );
    }
}
