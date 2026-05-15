package com.example.Ficha.medica.service;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Ficha.medica.client.MedicoClient;
import com.example.Ficha.medica.client.PacienteClient;
import com.example.Ficha.medica.dto.FichaMedicaDTO;
import com.example.Ficha.medica.dto.FichaMedicaResponse;
import com.example.Ficha.medica.model.FichaMedica;
import com.example.Ficha.medica.repository.FichaMedicaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FichaMedicaService {

    private final FichaMedicaRepository fichaMedicaRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;

    public FichaMedicaResponse crear(FichaMedicaDTO dto, String token) {

        log.info("Creando ficha médica",
                keyValue("paciente", dto.getNombrePaciente()));
        var paciente = pacienteClient.getPacienteClient(
                dto.getRunPaciente(),
                token);

        if (paciente == null) {
            throw new RuntimeException("El paciente no existe");
        }
        var medico = medicoClient.getMedicoClient(
                dto.getNombreMedico(),
                token);

        if (medico == null) {
            throw new RuntimeException("El médico no existe");
        }

        FichaMedica fichaMedica = fichaMedicaRepository.save(
                new FichaMedica(
                        null,
                        dto.getRunPaciente(),
                        dto.getNombrePaciente(),
                        dto.getNombreMedico(),
                        dto.getProcedimiento(),
                        dto.getQueMedicamentoEstaTomando(),
                        dto.getEnfermedad(),
                        dto.getAlergias(),
                        dto.getOdontograma()));
        return mapToResponse(fichaMedica, token);
    }

    public List<FichaMedicaResponse> listar(String token) {
        return fichaMedicaRepository.findAll()
                .stream()
                .map(ficha -> mapToResponse(ficha, token))
                .toList();
    }

    public FichaMedicaResponse obtener(Long id, String token) {
        FichaMedica fichaMedica = fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ficha médica no encontrada"));
        return mapToResponse(fichaMedica, token);
    }

    public FichaMedicaResponse actualizar(
            Long id,
            FichaMedicaDTO dto,
            String token) {
        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(),token);
        if (paciente == null) {
            throw new RuntimeException("El paciente no existe");
        }
        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(),token);
        if (medico == null) {
            throw new RuntimeException("El médico no existe");
        }

        FichaMedica ficha = fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ficha médica no encontrada"));
        ficha.setRunPaciente(dto.getRunPaciente());
        ficha.setNombrePaciente(dto.getNombrePaciente());
        ficha.setNombreMedico(dto.getNombreMedico());
        ficha.setProcedimiento(dto.getProcedimiento());
        ficha.setQueMedicamentoEstaTomando(dto.getQueMedicamentoEstaTomando());
        ficha.setEnfermedad(dto.getEnfermedad());
        ficha.setAlergias(dto.getAlergias());
        ficha.setOdontograma(dto.getOdontograma());
        FichaMedica fichaActualizada =
                fichaMedicaRepository.save(ficha);

        return mapToResponse(fichaActualizada, token);
    }

    public void eliminar(Long id) {
        FichaMedica fichaMedica = fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ficha médica no encontrada"));
        fichaMedicaRepository.delete(fichaMedica);
    }

    private FichaMedicaResponse mapToResponse(FichaMedica fichaMedica, String token) {
        var paciente = pacienteClient.getPacienteClient(fichaMedica.getRunPaciente(), token);
        var medico = medicoClient.getMedicoClient(fichaMedica.getNombreMedico(), token);
        return FichaMedicaResponse.builder()
                .id(fichaMedica.getId())
                .runPaciente(fichaMedica.getRunPaciente())
                .nombrePaciente(paciente.getNombrePaciente())
                .nombreMedico(medico.getNombreMedico())
                .procedimiento(fichaMedica.getProcedimiento())
                .queMedicamentoEstaTomando(paciente.getQueMedicamentoEstaTomando())
                .enfermedad(paciente.getEnfermedad())
                .alergias(paciente.getAlergias())
                .odontograma(fichaMedica.getOdontograma())
                .build();
    }
}