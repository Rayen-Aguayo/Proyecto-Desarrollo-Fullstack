package com.example.Ficha.medica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private String runPaciente;
    private String nombrePaciente;
    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;

}
