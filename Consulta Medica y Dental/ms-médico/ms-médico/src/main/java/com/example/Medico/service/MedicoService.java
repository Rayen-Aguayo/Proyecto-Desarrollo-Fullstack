package com.example.Medico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Medico.model.Medico;
import com.example.Medico.repository.MedicoRepository;

public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public Medico findById (String id)
}
