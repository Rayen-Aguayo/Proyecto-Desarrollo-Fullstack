package com.example.Receta.Medica.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaMedicaResponce {
    
    private String nomMedicamento;
    private Integer diasTomarMedicamento;
    private Date inicioReceta;
    private MedicoDTO nomMédico;
    private MedicoDTO runMedico;
    private Integer cantTomarDia;
    private String firmaMédico;
}
