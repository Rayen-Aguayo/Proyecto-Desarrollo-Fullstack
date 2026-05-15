package com.example.Experiencia.del.Paciente.service;

import org.springframework.stereotype.Service;
import com.example.Experiencia.del.Paciente.client.MedicoClient;
import com.example.Experiencia.del.Paciente.client.PacienteClient;
import com.example.Experiencia.del.Paciente.dto.OpinionPacienteDTO;
import com.example.Experiencia.del.Paciente.dto.OpinionPacienteResponse;
import com.example.Experiencia.del.Paciente.model.OpinionPaciente;
import com.example.Experiencia.del.Paciente.repository.OpinionPacienteRepository;

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
        log.info("Registrando nueva opinión para el paciente: {}", dto.getRunPaciente());
        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);
        if (paciente == null) {
            throw new RuntimeException("El paciente no existe, no se puede registrar la opinión");
        }
        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(),token);
        if (medico == null) {
            throw new RuntimeException("El médico no existe");
        }

        OpinionPaciente opinionPaciente = OpinionPacienteRepository.save(
                new OpinionPaciente(
                        null,
                        dto.getRunPaciente(),
                        dto.getNombreMedico(),
                        dto.getAtencionMedico(),
                        dto.getExpliqueSuPuntuacion(),
                        dto.getExplicacionTratamiento(),
        return mapToResponse(opinionPaciente, token);

        OpinionPaciente guardada = opinionPacienteRepository.save(opinionPaciente);

        return mapToResponse(guardada);
    }

    public List<OpinionPacienteResponse> listar() {
        return opinionPacienteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OpinionPacienteResponse obtener(Long id) {
        OpinionPaciente opinion = opinionPacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opinión no encontrada"));
        return mapToResponse(opinion);
    }

    public void eliminar(Long id) {
        if (!opinionPacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar, opinión no encontrada");
        }
        opinionPacienteRepository.deleteById(id);
    }

    // Método para convertir el Modelo a Response
    private OpinionPacienteResponse mapToResponse(OpinionPaciente opinion) {
        return OpinionPacienteResponse.builder()
                .id(opinion.getId())
                .runPaciente(opinion.getRunPaciente())
                .nombreMedico(opinion.getNombreMedico())
                .atencionMedico(opinion.getAtencionMedico())
                .expliqueSuPuntuacion(opinion.getExpliqueSuPuntuacion())
                .explicacionTratamiento(opinion.getExplicacionTratamiento())
                .comentarioMejora(opinion.getComentarioMejora())
                .build();
    }
}
