package com.example.ms_pedir_hora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private String run;

    private Integer edad;
    private Integer telefono;
    private String especialidad;
}
