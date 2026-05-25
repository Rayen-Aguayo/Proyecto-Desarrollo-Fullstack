package com.example.Facturacion.y.Presupuesto.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Facturacion.y.Presupuesto.Service.FacturacionYPresupuestoService;
import com.example.Facturacion.y.Presupuesto.dto.ApiResponse;
import com.example.Facturacion.y.Presupuesto.dto.FacturacionYPresupuestoDTO;
import com.example.Facturacion.y.Presupuesto.dto.FacturacionYPresupuestoResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/facturacio-y-presupuesto")
@RequiredArgsConstructor
public class FacturacionYPresupuesto {
    private final FacturacionYPresupuestoService facturacionYPresupuestoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse<FacturacionYPresupuestoResponse>> crear(
            @Valid @RequestBody FacturacionYPresupuestoDTO dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.status(201).body(
                ApiResponse.<FacturacionYPresupuestoResponse>builder()
                        .success(true)
                        .message("Registro de atenciones creado")
                        .data(facturacionYPresupuestoService.crear(dto, token))
                        .build()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<List<FacturacionYPresupuestoResponse>>> listar(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                ApiResponse.<List<FacturacionYPresupuestoResponse>>builder()
                        .success(true)
                        .data(facturacionYPresupuestoService.listar(token))
                        .build()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ApiResponse<FacturacionYPresupuestoResponse>> obtener(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                ApiResponse.<FacturacionYPresupuestoResponse>builder()
                        .success(true)
                        .data(facturacionYPresupuestoService.obtener(id, token))
                        .build()
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse<FacturacionYPresupuestoResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FacturacionYPresupuestoDTO dto,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                ApiResponse.<FacturacionYPresupuestoResponse>builder()
                        .success(true)
                        .message("Registro de atenciones actualizado")
                        .data(facturacionYPresupuestoService.actualizar(id, dto, token))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        facturacionYPresupuestoService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Registro de atenciones eliminado")
                        .build()
        );
    }
}
