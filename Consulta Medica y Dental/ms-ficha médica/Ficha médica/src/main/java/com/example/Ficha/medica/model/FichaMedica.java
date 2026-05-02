package com.example.Ficha.medica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fichaMedica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaMedica {
    @Id
    private String nombrePaciente;
    private Integer run;
    private String medico;
    private String procedimiento;
    private String medicamentoQueToma;
    private String enfermedad;
    private String alergias;
    private String odontograma;
}
