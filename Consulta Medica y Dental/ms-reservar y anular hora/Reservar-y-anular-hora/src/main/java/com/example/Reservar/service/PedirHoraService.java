package com.example.Reservar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PedirHoraService {
    @Autowired
    private PedirHoraService pedirHoraService;

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
    
}
