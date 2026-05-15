package com.example.Experiencia.del.Paciente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpinionPacienteResponse {
    private Long id;
    private String runPaciente;
    private String nombreMedico;
    private Integer atencionMedico;
    private String expliqueSuPuntuacion;
    private String explicacionTratamiento;
    private String comentarioMejora;
    private Integer puntuacionMedico;
}
