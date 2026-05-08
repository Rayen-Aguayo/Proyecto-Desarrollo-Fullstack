package com.example.Medico.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class MedicoDTO {
    
    @NotBlank(message =  "El run no puede estar vacio")
    private String run;

    @NotBlank(message =  "El nombre no puede estar vacio")
    private String nombreMedico;

    @NotNull(message = "La edad del medico no puede estar vacio")
    @Min(value = 0, message = "La edad debe ser positiva")
    private Integer edad;

    @NotNull(message = "El numero del telefono es obligatorio")
    @Size(min = 9, max = 9, message = "El numero del telefono debe tener 9 digitos")
    private Integer nrotelefono;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    public Object getNombreMedico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombreMedico'");
    }

    public Integer getEspecialidad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEspecialidad'");
    }

    public Integer getFirmaMedico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirmaMedico'");
    }

    public String getEdad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEdad'");
    }

    public String getNroTelefono() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNroTelefono'");
    }

    public String getRun() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRun'");
    }





}
