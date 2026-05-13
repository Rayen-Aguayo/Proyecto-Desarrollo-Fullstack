package com.example.Ficha.medica.service;

import static net.logstash.logback.argument.StructuredArguments.f;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ficha.medica.client.MedicoClient;
import com.example.Ficha.medica.client.PacienteClient;
import com.example.Ficha.medica.dto.FichaMedicaDTO;
import com.example.Ficha.medica.dto.FichaMedicaResponse;
import com.example.Ficha.medica.model.FichaMedica;
import com.example.Ficha.medica.repository.FichaMedicaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FichaMedicaService {
    @Autowired
    private FichaMedicaRepository fichaMedicaRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;

    public FichaMedicaResponse crear(FichaMedicaDTO dto, String token) {
        log.info("Crear FichaMedica", keyValue("Paciente", dto.getNombrePaciente()));

        var paciente =pacienteClient.getPacienteClient(dto.getRun(), token);

        if(paciente == null) {
            throw new RuntimeException("El Paciente no tiene una Ficha Medica");
        }

        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(), token);
        
        if (medico == null) {
            throw new RuntimeException("Este nombre Medico");
        }

        FichaMedica fichaMedica = fichaMedicaRepository.save(
            new FichaMedica(
                null,
                    dto.getRun(),
                    dto.getNombrePaciente(),
                    dto.getNombreMedico(),
                    dto.getProcedimiento(),
                    dto.getQueMedicamentoEstaTomando(),
                    dto.getEnfermedad(),
                    dto.getAlergias(),
                    dto.getOdontograma()
            )
    );
    
    return mapToResponse(fichaMedica, token);

    }

    public List<FichaMedicaResponse> listar(String token) {
        return FichaMedicaRepository.findAll()
                .stream()
                .map()
                .map(l -> mapToResponse(l, token))
                .toList();
    }

    public FichaMedicaResponse obtener(Long id, String token) {
        FichaMedica fichaMedica = fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ficha Medica no encontrado"));
        return mapToResponse(fichaMedica, token);
    }

    public FichaMedica actualizar(Long id, FichaMedicaDTO dto, String token) {
        var paciente = pacienteClient.getPacienteClient(dto.getRun(), token);

        if (paciente == null) {
            throw new RuntimeException("El run de paciente no aparece o no existe");
        }

        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(), token);

        if (medico == null) {
            throw new RuntimeException("El nombre de medico no aparece o no existe");
        }

        FichaMedica f = fichaMedicaRepository.findById(id)
                      .orElseThrow(() -> new EntityNotFoundException("Ficha Medica no encontrado"));
        f.setNombrePaciente(dto.getNombrePaciente());
        f.setNombreMedico(dto.getNombreMedico());
        f.setProcedimiento(dto.getProcedimiento());
        f.setQueMedicamentoEstaTomando(dto.getQueMedicamentoEstaTomando());
        f.setEnfermedad(dto.getEnfermedad());
        f.setAlergias(dto.getAlergias());
        f.setOdontograma(dto.getOdontograma());

        return mapToResponse(fichaMedicaRepository.save(f), token);
    }

    public void eliminar(Long id) {
        FichaMedicaRepository.deleteById(id);
    }

    private FichaMedicaResponse mapToResponse(FichaMedica fichaMedica,String token) {
    var paciente = pacienteClient.getPacienteClient(
            fichaMedica.getRun(),token
    );
    var medico = medicoClient.getMedicoClient(
            fichaMedica.getNombreMedico(),token
    );
    return new FichaMedicaResponse(
        fichaMedica.getId(),
        fichaMedica.getRun(),
        fichaMedica.getNombrePaciente(),
        fichaMedica.getNombreMedico(),
        fichaMedica.getProcedimiento(),
        fichaMedica.getQueMedicamentoEstaTomando(),
        fichaMedica.getEnfermedad(),
        fichaMedica.getAlergias(),
        fichaMedica.getOdontograma()
    );
    
    }
}
