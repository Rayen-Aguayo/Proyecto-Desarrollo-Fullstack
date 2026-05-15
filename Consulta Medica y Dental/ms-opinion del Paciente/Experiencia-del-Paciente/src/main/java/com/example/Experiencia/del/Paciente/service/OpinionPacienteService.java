package com.example.Experiencia.del.Paciente.service;

import org.springframework.stereotype.Service;
import com.example.Experiencia.del.Paciente.client.MedicoClient;
import com.example.Experiencia.del.Paciente.client.PacienteClient;
import com.example.Experiencia.del.Paciente.dto.OpinionPacienteDTO;
import com.example.Experiencia.del.Paciente.dto.OpinionPacienteResponse;
import com.example.Experiencia.del.Paciente.model.OpinionPaciente;
import com.example.Experiencia.del.Paciente.repository.OpinionPacienteRepository;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionPacienteService {

    private final OpinionPacienteRepository opinionPacienteRepository;
    private final MedicoClient medicoClient;
    private final PacienteClient pacienteClient;

    public OpinionPacienteResponse crear(OpinionPacienteDTO dto, String token) {

        log.info("Registrando nueva opinión para el paciente: {}", keyValue("run del paciente", dto.getRunPaciente()));
        
        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);
        if (paciente == null) {
            throw new RuntimeException("El paciente no existe, no se puede registrar la opinión");
        }

        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(),token);

        if (medico == null) {
            throw new RuntimeException("El médico no existe");
        }

        OpinionPaciente opinionPaciente = opinionPacienteRepository.save(
                new OpinionPaciente(
                        null,
                        dto.getRunPaciente(),
                        dto.getNombreMedico(),
                        dto.getAtencionMedico(),
                        dto.getExpliqueSuPuntuacion(),
                        dto.getExplicacionTratamiento(),
                        dto.getComentarioMejora(),
                        dto.getPuntuacionMedico())
        );

        return mapToResponse(opinionPaciente, token);

    }

    public List<OpinionPacienteResponse> listar(String token) {
        return opinionPacienteRepository.findAll()
                .stream()
                .map(o -> mapToResponse(o, token))
                .toList();
    }

    public OpinionPacienteResponse obtener(Long id,String token) {
        OpinionPaciente opinion = opinionPacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opinión no encontrada"));
        return mapToResponse(opinion, token);
    }

    public void eliminar(Long id) {
        if (!opinionPacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar, opinión no encontrada");
        }
        opinionPacienteRepository.deleteById(id);
    }

    // Método para convertir el Modelo a Response
    private OpinionPacienteResponse mapToResponse(OpinionPaciente opinion, String token) {
        var paciente = pacienteClient.getPacienteClient(opinion.getRunPaciente(), token);
        var medico = medicoClient.getMedicoClient(opinion.getNombreMedico(), token);
        return OpinionPacienteResponse.builder()
                .id(opinion.getId())
                .runPaciente(paciente)
                .nombreMedico(medico)
                .atencionMedico(opinion.getAtencionMedico())
                .expliqueSuPuntuacion(opinion.getExpliqueSuPuntuacion())
                .explicacionTratamiento(opinion.getExplicacionTratamiento())
                .comentarioMejora(opinion.getComentarioMejora())
                .build();
    }
}
