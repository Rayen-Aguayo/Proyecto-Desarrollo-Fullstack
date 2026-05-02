package com.example.Ficha.medica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombrePaciente;
    @NotBlank(message = "El run no puede estar vacio")
    private Integer run;
    @NotBlank(message = "El nombre del medico es obligatorio")
    private String medico;
    private String procedimiento;
    private String medicamentoQueToma;
    private String enfermedad;
    private String alergias;
    private String odontograma;
}
