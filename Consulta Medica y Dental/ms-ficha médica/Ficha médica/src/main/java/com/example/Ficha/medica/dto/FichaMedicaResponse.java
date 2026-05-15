package com.example.Ficha.medica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FichaMedicaResponse {
    private Long id;
    private PacienteResponse ruPaciente;
    private PacienteResponse nombrePaciente;
    private MedicoResponse nombreMedico;
    private PacienteResponse queMedicamentoEstaTomado;
    private PacienteResponse enfermedad;
    private PacienteResponse alergias;
    private String odontograma;
}
