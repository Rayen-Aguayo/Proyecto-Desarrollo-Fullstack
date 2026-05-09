package com.example.Pagos.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String runPaciente;  
    private String nombrePaciente;
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
