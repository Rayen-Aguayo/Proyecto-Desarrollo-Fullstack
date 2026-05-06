package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    @NotBlank(message = "el run no puede estar vacio")
    private String run;

    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombrePaciente;

    @NotBlank(message = "los datos del paciente no pueden esta vacios")
    private String datosDelPaciente;

    @NotNull(message = "la edad del paciente no puede estar vacio")
    @Min(value = 0, message = "la edad debe ser positiva")
    private Integer edad;

    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;

    @NotNull(message = "el nro de Telefono no puede estar vacio")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 digitos")
    private Integer nroTelefono;
}
