package com.example.Reservar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Reservar.dto.MedicoDTO;
import com.example.Reservar.dto.PacienteDTO;
import com.example.Reservar.dto.PedirHoraResponseDTO;
import com.example.Reservar.model.PedirHora;
import com.example.Reservar.repository.PedirHoraRepository;

import jakarta.validation.constraints.NotBlank;

@Service
public class PedirHoraService {
    @Autowired
    private PedirHoraRepository pedirHoraRepository;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    public List<PedirHoraResponseDTO> getAll(){
    List<PedirHoraResponseDTO> lista = new ArrayList<>();

 
    private PacienteDTO rutPaciente;
    private PacienteDTO nombrePaciente;
    private MedicoDTO nombreMdico;
    private String atencion;
    for(PedirHora p: pedirHoraRepository.findAll()){
        PedirHoraResponseDTO pedirHora = new PedirHoraResponseDTO();
        pedirHora.setId(p.getId());
        pedirHora.setFecha(p.getFecha());
        pedirHora.setHoraDeAtención(p.getHoraDeAtención());
        PacienteDTO paciente = get
        AutorDTO autor = getAutor(l.getId_autor());
        libro.setAutor(autor);
        lista.add(libro);



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
    
}
