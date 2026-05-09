package com.example.Pagos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosResponse {

    private Long id;
    
    private PacienteResponse runPaciente;  
    private PacienteResponse nombrePaciente;
    private Date fecha;
    private Integer Hora;
    private String MétodoPago;
    private Integer nroBoleta;
    private String RegistroFacturación;
    private Double neto;
    private Double iva;
    private Double total; 
    private String estado; 

}
