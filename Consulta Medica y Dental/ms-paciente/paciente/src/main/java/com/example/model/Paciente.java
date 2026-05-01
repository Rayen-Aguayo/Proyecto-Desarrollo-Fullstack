package com.example.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @NotBlank(message = "el run no puede estar vacio")
    private String run;

    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombrePaciente;

    @NotBlank(message = "el run no puede estar vacio")
    private String datosDelPaciente;

    @NotNull(message = "la fecha de nacimiento no puede estar vacio")
    private Integer edad;

    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;

    @NotNull(message = "el nro de Telefono no puede estar vacio")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 digitos")
    private Integer nroTelefono;
}
