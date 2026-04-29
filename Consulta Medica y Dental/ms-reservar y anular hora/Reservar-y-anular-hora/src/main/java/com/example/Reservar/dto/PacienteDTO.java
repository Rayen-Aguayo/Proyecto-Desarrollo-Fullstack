package com.example.Reservar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    private String run;
    
    private String datosDelPaciente;
    private Integer fechaNacimiento;
    private String alergias;
    private String enfermedad;
    private Boolean tomaMedicamento;
    private String cualMedicameto;
    private Integer nroTelefono;

}
