package com.example.Reservar.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Reservar.dto.ApiResponse;
import com.example.Reservar.dto.PacienteDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PacienteClient {

    private final WebClient webClient;
    private final String BASE_URL = "http://localhost:8080/api/v1/pacientes/"
    
    public PacienteDTO getPacienteDTO(String run, String token){
    
    ApiResponse<PacienteDTO> response = webClient.get()
                .uri(BASE_URL + run)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono((ParameterizedTypeReference<T>) new org.springframework.core.ParameterizedTypeReference<ApiResponse<AutorResponse>>() {})
                .block();

        return response != null ? response.getData() : null;
}
