package com.example.Reservar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Reservar.client.MedicoClient;
import com.example.Reservar.client.PacienteClient;
import com.example.Reservar.dto.MedicoDTO;
import com.example.Reservar.dto.PacienteDTO;
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
    @Autowired
    private PedirHoraRepository pedirHoraRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;


    public PedirHoraResponse  crear(PedirHoraDTO dto, String token) {

        log.info("reservar hora", keyValue("nombre del paciente", dto.getNombrePaciente()));

        var pedirHora = pacienteClient.getPacienteClient(dto.getRutPaciente(), token);

        if (pedirHora == null) {
            throw new RuntimeException("el paciente no existe no se le puede reservar una hora");
        }

        PedirHora pedirHora1 = pedirHoraRepository.save(
                new PedirHora(null, dto.getRutPaciente(),dto.getNombrePaciente(), dto.getNombreMedico(),
                dto.getFecha(),dto.getHoraDeAtención(), dto.getAtencion())
        );

        return mapToResponse(pedirHora1, token);
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

        var pedirHora = pacienteClient.getPacienteClient(dto.getRutPaciente(), token);

        if (pedirHora == null) {
            throw new RuntimeException("la reserva de hora no existe");
        }

        PedirHora p = pedirHoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("reserva de hora no encontrado"));


        p.setRunPaciente(dto.getRutPaciente());
        p.setNombrePaciente(dto.getNombrePaciente());
        p.setNombreMedico(dto.getNombreMedico());
        p.setFecha(dto.getFecha());
        p.setHoraDeAtención(dto.getHoraDeAtención());

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
                .nombreMédico(medico)
                .fecha(pedirHora.getFecha())
                .horaDeAtención(pedirHora.getHoraDeAtención())  
                .atencion(pedirHora.getAtencion())

                .build();
    }
}