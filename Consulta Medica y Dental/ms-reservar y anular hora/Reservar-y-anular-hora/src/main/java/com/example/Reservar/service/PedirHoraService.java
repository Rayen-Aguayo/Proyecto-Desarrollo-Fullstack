package com.example.Reservar.service;

import org.apache.tomcat.websocket.WsFrameClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class PedirHoraService {
    @Autowired
    private PedirHoraService pedirHoraService;

    @Bean
    public WsFrameClient webClient(){
        return WebClient.builder().build();
    }
    
}
