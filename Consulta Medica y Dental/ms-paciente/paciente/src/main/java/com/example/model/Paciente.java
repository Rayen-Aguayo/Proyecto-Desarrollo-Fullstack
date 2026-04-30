package com.example.model;

import java.sql.Date;

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
    private Date fechaNacimiento;
    private String alergias;
    private String enfermedad;
    private String queMedicamentoEstaTomando;
    private Integer nroTelefono;
}
