package com.example.Pagos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagosResponse {

    private Long id;
    
    private PacienteResponse runPaciente;  
    private PacienteResponse nombrePaciente;
    private Date fecha;
    private Integer hora;
    private String métodoPago;
    private Integer nroBoleta;
    private String registroFacturación;
    private Double neto;
    private Double iva;
    private Double total; 
    private String estado; 

}
