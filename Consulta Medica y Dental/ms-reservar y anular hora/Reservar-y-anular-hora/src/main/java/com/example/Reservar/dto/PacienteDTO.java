package com.example.Reservar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private String runPaciente;

    private String nombrePaciente;

    private String datosDelPaciente;

    private Integer edad;

    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;

    private Integer nroTelefonoPaciente;
}
