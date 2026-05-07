package com.example.Reservar.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Reservar.dto.ApiResponse;
import com.example.Reservar.dto.MedicoDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MedicoClient {
    private final WebClient webClient;

    private final String BASE_URL = "http://localhost:8080/api/v1/medicos/";
    
    public MedicoDTO getMedicoClient(String run, String token){
    
    ApiResponse<MedicoDTO> response = webClient.get()
                .uri(BASE_URL + run)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(new org.springframework.core.ParameterizedTypeReference<ApiResponse<MedicoDTO>>() {})
                .block();

        return response != null ? response.getData() : null;
        }
}
