package com.example.ms_paciente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String datosDelPaciente;
    private Integer fechaNacimiento;
    private String alergias;
    private String enfermedad;
    private Boolean tomaMedicamento;
    private String cualMedicameto;
    private Integer nroTelefono;
}
