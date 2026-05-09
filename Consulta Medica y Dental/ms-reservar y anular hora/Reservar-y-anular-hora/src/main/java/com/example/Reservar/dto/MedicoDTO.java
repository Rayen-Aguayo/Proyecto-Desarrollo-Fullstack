package com.example.Reservar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    
    private String runMeico;
    private String nombreMedico;
    private String especialidad;
}
