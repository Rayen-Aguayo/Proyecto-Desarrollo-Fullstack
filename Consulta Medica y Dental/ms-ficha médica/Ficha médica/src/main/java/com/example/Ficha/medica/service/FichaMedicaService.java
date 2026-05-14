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

        var pacienteNom = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);
        if(pacienteNom == null) {
            throw new RuntimeException("El Paciente no existe");
        }
        var medicoNom = medicoClient.getMedicoClient(dto.getNombreMedico(), token);
        if (medicoNom == null) {
            throw new RuntimeException("Este nombre Medico");
        }

        var pacienteMedicamento = pacienteClient.getPacienteClient(dto.getQueMedicamentoEstaTomando(), token);
        if (pacienteMedicamento == null) {
            throw new RuntimeException("El medicamento no existe");
        }
        var pacienteEnfermedad = pacienteClient.getPacienteClient(dto.getEnfermedad(), token);
        if (pacienteEnfermedad == null) {
            throw new RuntimeException("Esta enfermedad no existe");
        }
        var pacienteAlergias = pacienteClient.getPacienteClient(dto.getAlergias(), token);
        if (pacienteAlergias == null) {
            throw new RuntimeException("Esta alergia no existe");
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
                    dto.getOdontograma()
            )
    );
    
    return mapToResponse(fichaMedica, token);

    }

    public List<FichaMedicaResponse> listar(String token) {
        return FichaMedicaRepository.findAll()
                .stream()
                .map(l -> mapToResponse(l, token))
                .toList();
    }

    public FichaMedicaResponse obtener(Long id, String token) {
        FichaMedica fichaMedica = fichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ficha Medica no encontrado"));
        return mapToResponse(fichaMedica, token);
    }

    public FichaMedica actualizar(Long id, FichaMedicaDTO dto, String token) {
        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);
        if (paciente == null) {
            throw new RuntimeException("El run de paciente no aparece o no existe");
        }
        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(), token);
        if (medico == null) {
            throw new RuntimeException("El nombre de medico no aparece o no existe");
        }
        var pacienteMedicamento = pacienteClient.getPacienteClient(dto.getQueMedicamentoEstaTomando(), token);
        if (pacienteMedicamento == null) {
            throw new RuntimeException("El medicamento no existe");
        }
        var pacienteEnfermedad = pacienteClient.getPacienteClient(dto.getEnfermedad(), token);
        if (pacienteEnfermedad == null) {
            throw new RuntimeException("Esta enfermedad no existe");
        }
        var pacienteAlergias = pacienteClient.getPacienteClient(dto.getAlergias(), token);
        if (pacienteAlergias == null) {
            throw new RuntimeException("Esta alergia no existe");
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
    var pacienteRun = pacienteClient.getPacienteClient(fichaMedica.getRunPaciente(), token);
    var pacienteNom = pacienteClient.getPacienteClient(fichaMedica.getNombrePaciente(), token)
    var medicoNom = medicoClient.getMedicoClient(fichaMedica.getNombreMedico(),token);
    var pacienteMedicamento = pacienteClient.getPacienteClient(fichaMedica.getQueMedicamentoEstaTomando(), token);
    var pacienteEnfermedad = pacienteClient.getPacienteClient(fichaMedica.getEnfermedad(), token)
    var pacienteAlergias = pacienteClient.getPacienteClient(fichaMedica.getAlergias(), token);
 
    return FichaMedicaResponse.builder(
        .id(fichaMedica.getId())
        .runPaciente(pacienteRun)
        .nombrePaciente(pacienteNom)
        .nombreMedico(medicoNom)
        .queMedicamentoEstaTomando(pacienteMedicamento)


    );

    }
}