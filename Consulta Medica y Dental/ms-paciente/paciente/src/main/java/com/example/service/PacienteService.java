package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.PacienteDTO;
import com.example.model.Paciente;
import com.example.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente crear(PacienteDTO dto) {
        log.info("Crear paciente", keyValue("nombre", dto.getNombrePaciente()));

        Paciente a = new Paciente(dto.getRun(), dto.getNombrePaciente(), 
        dto.getDatosDelPaciente(), dto.getEdad(), dto.getAlergias(), 
        dto.getEnfermedad(), dto.getQueMedicamentoEstaTomando(), dto.getNroTelefono());
 
        return pacienteRepository.save(a);
    }

    public List<Paciente> listar() {
        log.info("Listar autores");
        return pacienteRepository.findAll();
    }

    public Paciente obtener(String id) {
        log.info("Obtener autor", keyValue("id", id));

        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    }

    public Paciente actualizar(String id, PacienteDTO dto) {
        log.info("Actualizar autor", keyValue("id", id));

        Paciente a = obtener(id);
        a.setNombre(dto.getNombre());
        a.setAnio(dto.getAnio());

        return pacienteRepository.save(a);
    }

    public void eliminar(String id) {
        log.warn("Eliminar autor", keyValue("id", id));
        pacienteRepository.deleteById(id);
    }
}
