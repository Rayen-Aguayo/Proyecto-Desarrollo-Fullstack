package com.example.Reservar.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedirHoraDTO {

    
    @NotNull(message = "la fecha no puede estar vacia")
    private Date fecha;

    @NotNull(message = "la hora de atencion no puede estar vacio")
    private Integer horaDeAtención;  
    @NotBlank(message = "el run del paciente no puede estar vacio")
    private String rutPaciente;
    @NotBlank(message = "el nombre del paciente no puede estar vacio")
    private String nombrePaciente;
    @NotBlank(message = "el nombre del medico no puede estar vacio")
    private String nombreMedico;
    @NotBlank(message = "la atencion que va a recibir el paciente no puede estar vacio")
    private String atencion;
}
