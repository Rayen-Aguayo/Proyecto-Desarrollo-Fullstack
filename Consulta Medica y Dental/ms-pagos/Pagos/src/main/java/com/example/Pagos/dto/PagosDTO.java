package com.example.Pagos.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosDTO {
    @NotBlank
    private String runPaciente;  
    @NotBlank
    private String nombrePaciente;
    
    private Date fecha;
    private Integer hora;
    @NotBlank
    private String métodoPago;
    @NotNull
    private Integer nroBoleta;
    @NotBlank
    private String registroFacturación;
    @NotNull
    private Double neto;
    @NotNull
    private Double iva;
    
    private Double total; 
    @NotBlank
    private String estado; 

}
