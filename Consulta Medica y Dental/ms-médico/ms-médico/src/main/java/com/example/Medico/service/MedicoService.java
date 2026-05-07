package com.example.Medico.service;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Medico.dto.MedicoDTO;
import com.example.Medico.model.Medico;
import com.example.Medico.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicoService {

    private final MedicoRepository repo;

    public Medico crear(MedicoDTO dto) {
        log.info("Crear medico", keyValue("nombre", dto.getNombreMedico()));

        Medico m = new Medico(dto.getRun(), dto.getNombreMedico(),
        dto.get)
    }
}
