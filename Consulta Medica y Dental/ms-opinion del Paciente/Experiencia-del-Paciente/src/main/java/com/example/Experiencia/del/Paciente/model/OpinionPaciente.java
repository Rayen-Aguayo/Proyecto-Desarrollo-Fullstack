package com.example.Experiencia.del.Paciente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opinion_paciente")
public class OpinionPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String runPaciente;
    private String nombreMedico;
    private Integer atencionMedico;
    private String explicacionTratamiento;
    private String comentario;
    private Integer puntuacionMedico;
}

