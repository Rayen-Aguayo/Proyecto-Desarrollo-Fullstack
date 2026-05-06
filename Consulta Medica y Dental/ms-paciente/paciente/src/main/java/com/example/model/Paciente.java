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
    private String run;

    private String nombrePaciente;

    private String datosDelPaciente;

    private Integer edad;

    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;

    private Integer nroTelefono;
}
