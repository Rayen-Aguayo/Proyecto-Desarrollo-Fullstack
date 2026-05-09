package com.example.Pagos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Pagos.client.PacienteClient;
import com.example.Pagos.dto.PagosDTO;
import com.example.Pagos.dto.PagosResponse;
import com.example.Pagos.model.Pagos;
import com.example.Pagos.repository.PagosRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
@RequiredArgsConstructor
@Slf4j
public class PagosService {
    
    private final PagosRepository pagosRepository;
    private final PacienteClient pacienteClient;

    public PagosResponse  crear(PagosDTO dto, String token) {

        log.info("registrar pago", keyValue("nombre paciente", dto.getRunPaciente()));

        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);

        if (paciente == null) {
            throw new RuntimeException("El paciente no existe");
        }

        Pagos pagos = pagosRepository.save(
                new Pagos(null,dto.getRunPaciente(), dto.getNombrePaciente(),dto.getFecha(),
                         dto.getHora(),dto.getMétodoPago(),dto.getNroBoleta(),dto.getRegistroFacturación(),
                         dto.getNeto(),dto.getIva(),dto.getTotal(), dto.getEstado())
        );
        return mapToResponse(pagos, token);
    }

    public List<PagosResponse> listar(String token) {

        return pagosRepository.findAll()
                .stream()
                .map(p -> mapToResponse(p, token))
                .toList();
    }

    public PagosResponse obtener(Long id, String token) {

        Pagos pagos = pagosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("el pago no existe"));

        return mapToResponse(pagos, token);
    }

    public PagosResponse actualizar(Long id, PagosDTO dto, String token) {

        var paciente = pacienteClient.getPacienteClient(dto.getRunPaciente(), token);

        if (paciente == null) {
            throw new RuntimeException("Autor no existe");
        }

        Pagos p = pagosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El pago no existe"));

        p.setRunPaciente(dto.getRunPaciente());
        p.setNombrePaciente(dto.getNombrePaciente());
        p.setFecha(dto.getFecha());
        p.setHora(dto.getHora());
        p.setMétodoPago(dto.getMétodoPago());
        p.setNroBoleta(dto.getNroBoleta());
        p.setRegistroFacturación(dto.getRegistroFacturación());
        p.setNeto(dto.getNeto());
        p.setIva(dto.getIva());
        p.setTotal(dto.getTotal());
        p.setEstado(dto.getEstado());

        return mapToResponse(pagosRepository.save(p), token);
    }

    public void eliminar(Long id) {
        pagosRepository.deleteById(id);
    }

    private PagosResponse mapToResponse(Pagos pagos, String token) {

        var paciente = pacienteClient.getPacienteClient(pagos.getRunPaciente(), token);

        return PagosResponse.builder()
                .id(pagos.getId())
                .titulo(pagos.getTitulo())
                .anio(pagos.getAnio())
                .autor(autor)
                .build();
    }
}