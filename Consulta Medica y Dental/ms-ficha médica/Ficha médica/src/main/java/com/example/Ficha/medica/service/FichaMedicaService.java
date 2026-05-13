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
            throw new RuntimeException("El Paciente no tiene una Ficha Medica")
        }

        FichaMedica fichaMedica = FichaMedicaRepository.save(
            new FichaMedica(null,dto.getRun(),dto.getNombrePaciente(),dto.getNombreMedico(),
            dto.getProcedimiento(),dto.getQueMedicamentoEstaTomando(),dto.getAlergias(),dto.getEnfermedad(),
            dto.getOdontograma())
        );

        return mapToResponse(fichaMedica, token);
    }

    public List<FichaMedica> listar() {
        log.info("Listar Fichas");
        return FichaMedicaRepository.findAll();
    }

    public FichaMedica obtener(String id) {
        log.info("Obtener Fichas", keyValue("run", id));

        return FichaMedicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico no encontrado"));
    }

    public FichaMedica actualizar(String id, FichaMedicaDTO dto) {
        log.info("Actualizar Medico", keyValue("id", id));

        FichaMedica f = obtener(id);
        f.setNombrePaciente(dto.getNombrePaciente());
        f.setNombreMedico(dto.getNombreMedico());
        f.setProcedimiento(dto.getProcedimiento());
        f.setQueMedicamentoEstaTomando(dto.getQueMedicamentoEstaTomando());
        f.setEnfermedad(dto.setEnfermedad(););
        f.setAlergias(dto.getAlergias());
        f.setOdontograma(dto.getOdontograma());

        return FichaMedicaRepository.save(f);
    }

    public void eliminar(String id) {
        log.warn("Eliminar Medico", keyValue("run", id));
        FichaMedicaRepository.deleteById(id);
    }
    }
}
