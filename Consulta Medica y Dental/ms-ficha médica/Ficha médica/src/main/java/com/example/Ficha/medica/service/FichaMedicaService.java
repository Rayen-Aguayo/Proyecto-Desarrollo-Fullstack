package com.example.Ficha.medica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ficha.medica.dto.FichaMedicaDTO;
import com.example.Ficha.medica.model.FichaMedica;
import com.example.Ficha.medica.repository.FichaMedicaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FichaMedicaService {

    private final FichaMedicaRepository fichaMedicaRepository;

    public FichaMedica crear(FichaMedicaDTO dto) {
        log.info("Crear FichaMedica", keyValue(""));
    }
}
