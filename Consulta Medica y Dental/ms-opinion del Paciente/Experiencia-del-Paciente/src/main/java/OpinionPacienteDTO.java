

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OpinionPacienteDTO {
    @NotBlank(message = "El run del Paciente es obligatorio")
    private String runPaciente;
    @NotBlank(message = "El nombre del Medico es obligaorio")
    private String nombreMedico;
    @NotNull(message = "La puntación es Obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 10, message = "La puntuación máxima es 10")
    private Integer atencionMedico;
    @NotBlank(message = "Es obligatorio comentar porque dejo esa opinion")
    private String expliqueSuPuntuacion;
    @NotNull(message = "Debe indicar si el doctor aclaró sus dudas")
    private String explicacionTratamiento;
    @NotNull(message = "Es obligatorio dejar un comentario para mejorar la calidad de atencion")
    private String comentarioMejora;
    @NotNull(message = "La puntuación general es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 10, message = "La puntuación máxima es 10")
    private Integer puntuacionGeneral;
}
