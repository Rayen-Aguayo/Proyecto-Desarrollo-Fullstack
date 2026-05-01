package com.example.Reservar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    
    private String run;
    private String nombre;
    private Integer edad;
    private Integer telefono;
    private String especialidad;
}
