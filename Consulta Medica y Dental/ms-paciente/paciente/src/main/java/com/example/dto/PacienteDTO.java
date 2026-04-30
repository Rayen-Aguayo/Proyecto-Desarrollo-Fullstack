package com.example.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    @NotBlank(message = "el run no puede estar vacio")
    @NotNull(message = "el run no puede estar vacio")
    private String run;

    @NotBlank(message = "el run no puede estar vacio")
    @NotNull(message = "el run no puede estar vacio")
    private String datosDelPaciente;

    @NotBlank(message = "la fecha de naciminento no puede estar vacio")
    @NotNull(message = "la fecha de nacimiento no puede estar vacio")
    private Date fechaNacimiento;

    private String alergias;
    private Long enfermedad;
    private String queMedicamentoEstaTomando;

    @NotBlank(message = "el nroTelefono no puede estar vacio")
    @NotNull(message = "el nroTelefono no puede estar vacio")
    private Long nroTelefono;
}
