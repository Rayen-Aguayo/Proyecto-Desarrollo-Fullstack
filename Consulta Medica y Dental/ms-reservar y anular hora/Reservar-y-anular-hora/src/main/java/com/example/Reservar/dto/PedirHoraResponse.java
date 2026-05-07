package com.example.Reservar.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedirHoraResponse {
    
    private Long id;
    
    private PacienteDTO runPaciente;
    private PacienteDTO nombrePaciente;
    private MedicoDTO nombreMédico;
    private Date fecha;
    private Integer horaDeAtención;  

    private String atencion;
}
