package com.example.Reservar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Reservar.dto.MedicoDTO;
import com.example.Reservar.dto.PacienteDTO;
import com.example.Reservar.dto.PedirHoraDTO;
import com.example.Reservar.model.PedirHora;
import com.example.Reservar.repository.PedirHoraRepository;
import static net.logstash.logback.argument.StructuredArguments.keyValue;


import jakarta.persistence.EntityNotFoundException;

@Service
public class PedirHoraService {
    @Autowired
    private PedirHoraRepository pedirHoraRepository;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    public List<PedirHoraDTO> getAll(){
    List<PedirHoraDTO> lista = new ArrayList<>();

    for(PedirHora p: pedirHoraRepository.findAll()){
        PedirHoraDTO pedirHora = new PedirHoraDTO();
        
        pedirHora.setId(p.getId());
        PacienteDTO pacienterun = getPacienteDTO(p.getRunPaciente());
        pedirHora.setRutPaciente(pacienterun);
        PacienteDTO nombrPaciente = getPacienteDTO(p.getNombrePaciente());
        pedirHora.setNombrePaciente(nombrPaciente);
        MedicoDTO nombrMedico = getMedicoDTO(p.getNombreMédico());
        pedirHora.setNombreMdico(nombrMedico);
        pedirHora.setFecha(p.getFecha());
        pedirHora.setHoraDeAtención(p.getHoraDeAtención());
        pedirHora.setAtencion(p.getAtencion());

        lista.add(pedirHora);

    }
    return lista;
}
        private PacienteDTO getPacienteDTO(String run){
        return webClient().get()
        .uri("http://localhost:8080/api/v1/pacientes/" + run)
        .retrieve()
        .bodyToMono(PacienteDTO.class)
        .block();
    }
    
    private MedicoDTO getMedicoDTO(String run){
        return webClient().get()
        .uri("http://localhost:8080/api/v1/medicos/" + run)
        .retrieve()
        .bodyToMono(MedicoDTO.class)
        .block();
    }

        public PedirHoraDTO crear(PacienteDTO dto) {
        log.info("agregar reserva de hora", keyValue("nombre", dto.getNombrePaciente()));

        PedirHora p = new PedirHora(null, dto.getRunPaciente(), dto.getNombrePaciente(),
    dto.get);
        return pedirHoraRepository.save(p);
    }

    private String nombreMédico;
    private Date fecha;
    private Integer horaDeAtención;  

    private String atencion;


    public List<PedirHora> listar() {
        log.info("Listar autores");
        return pedirHoraRepository.findAll();
    }

    public PedirHora obtener(Long id) {
        log.info("Obtener autor", keyValue("id", id));

        return pedirHoraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
    }

    public PedirHora actualizar(Long id, PacienteDTO dto) {
        log.info("Actualizar autor", keyValue("id", id));

        PedirHora p = obtener(id);
        a.setNombre(dto.getNombrePaciente());
        a.setAnio(dto.getAnio());

        return pedirHoraRepository.save(a);
    }

    public void eliminar(Long id) {
        log.warn("Eliminar autor", keyValue("id", id));
        pedirHoraRepository.deleteById(id);
    }
}

