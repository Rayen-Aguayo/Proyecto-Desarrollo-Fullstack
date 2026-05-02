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
public class PedirHoraResponseDTO {

    @NotNull(message = "el id no puede estar vacio")
    private Long id;
    
    private Date fecha;

    @NotNull(message = "la hora de atencion no puede estar vacio")
    private Integer horaDeAtención;  
    @NotBlank(message = "el run del paciente no puede estar vacio")
    private PacienteDTO rutPaciente;
    @NotBlank(message = "el nombre del paciente no puede estar vacio")
    private PacienteDTO nombrePaciente;
    @NotBlank(message = "el nombre del medico no puede estar vacio")
    private MedicoDTO nombreMdico;
    @NotBlank(message = "la atencion que va a recibir el paciente no puede estar vacio")
    private String atencion;
}
