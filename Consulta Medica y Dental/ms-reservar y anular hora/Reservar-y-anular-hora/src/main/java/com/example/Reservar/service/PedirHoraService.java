package com.example.Reservar.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Reservar.client.MedicoClient;
import com.example.Reservar.client.PacienteClient;
import com.example.Reservar.dto.PedirHoraDTO;
import com.example.Reservar.dto.PedirHoraResponse;
import com.example.Reservar.model.PedirHora;
import com.example.Reservar.repository.PedirHoraRepository;
import static net.logstash.logback.argument.StructuredArguments.keyValue;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedirHoraService {

    private final PedirHoraRepository pedirHoraRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;


    public PedirHoraResponse  crear(PedirHoraDTO dto, String token) {

        log.info("reservar hora", keyValue("run del paciente", dto.getRunPaciente()));

        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);

        if (paciente == null) {
            throw new RuntimeException("el paciente no existe no se le puede reservar una hora");
        }
        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(), token);
        if (medico == null) {
                throw new RuntimeException("El médico no existe");
}

        PedirHora pedirHora = pedirHoraRepository.save(
               new PedirHora(
                        null,
                        dto.getRunPaciente(),
                        dto.getNombrePaciente(),
                        dto.getNombreMedico(),
                        dto.getFecha(),
                        dto.getHoraDeAtencion(),
                        dto.getAtencion()
                    )
        );

        return mapToResponse(pedirHora, token);
    }

    public List<PedirHoraResponse> listar(String token) {

        return pedirHoraRepository.findAll()
                .stream()
                .map(p -> mapToResponse(p, token))
                .toList();
    }

    public PedirHoraResponse obtener(Long id, String token) {

        PedirHora pedirHora = pedirHoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("no se a encontrado ninguna reserva de hora"));

        return mapToResponse(pedirHora, token);
    }

    public PedirHoraResponse actualizar(Long id, PedirHoraDTO dto, String token) {

        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);

        if (paciente == null) {
            throw new RuntimeException("el paciente no existe");
        }
        var medico = medicoClient.getMedicoClient(dto.getNombreMedico(), token);

        if (medico == null) {
        throw new RuntimeException("El médico no existe");
        }
        PedirHora p = pedirHoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("reserva de hora no encontrado"));


        p.setRunPaciente(dto.getRunPaciente());
        p.setNombrePaciente(dto.getNombrePaciente());
        p.setNombreMedico(dto.getNombreMedico());
        p.setFecha(dto.getFecha());
        p.setHoraDeAtencion(dto.getHoraDeAtencion());

        return mapToResponse(pedirHoraRepository.save(p), token);
    }

    public void eliminar(Long id) {
        pedirHoraRepository.deleteById(id);
    }

    private PedirHoraResponse mapToResponse(PedirHora pedirHora, String token) {

        var pacienteRun = pacienteClient.getPacienteClient(pedirHora.getRunPaciente(), token);
        var pacienteNom = pacienteClient.getPacienteClient(pedirHora.getNombrePaciente(), token);
        var medico = medicoClient.getMedicoClient(pedirHora.getNombreMedico(), token);

        return PedirHoraResponse.builder()
                .id(pedirHora.getId())
                .runPaciente(pacienteRun)
                .nombrePaciente(pacienteNom)
                .nombreMedico(medico)
                .fecha(pedirHora.getFecha())
                .horaDeAtencion(pedirHora.getHoraDeAtencion())  
                .atencion(pedirHora.getAtencion())

                .build();
    }
}