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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedirHoraService {
    @Autowired
    private PedirHoraRepository pedirHoraRepository;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    
    private MedicoDTO getMedicoDTO(String run){
        return webClient().get()
        .uri("http://localhost:8080/api/v1/medicos/" + run)
        .retrieve()
        .bodyToMono(MedicoDTO.class)
        .block();
    }




}