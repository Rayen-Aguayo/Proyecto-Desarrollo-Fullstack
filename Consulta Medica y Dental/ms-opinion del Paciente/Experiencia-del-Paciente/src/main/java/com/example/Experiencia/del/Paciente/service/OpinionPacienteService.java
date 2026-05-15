package com.example.Experiencia.del.Paciente.service;

import org.springframework.stereotype.Service;

import com.example.Experiencia.del.Paciente.repository.OpinionPacienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionPacienteService {
    private final OpinionPacienteRepository opinionPacienteRepository;
    
}
