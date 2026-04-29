package com.example.Reservar.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedirHoraResponseDTO {

    private Long id;
    
    private Date fecha;
    private Integer horaDeAtención;
    private PacienteDTO nroPaciente;
    private PacienteDTO rutPaciente;
    private MedicoDTO nombreMédico;
    private String atencion;

}
