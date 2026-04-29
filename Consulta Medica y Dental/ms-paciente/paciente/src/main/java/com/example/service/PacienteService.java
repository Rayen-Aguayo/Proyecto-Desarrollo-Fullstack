package com.example.ms_paciente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_paciente.model.Paciente;
import com.example.ms_paciente.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Paciente findById (String id){
        return pacienteRepository.findById(id).get();
    }

    public Paciente save (Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void delete(String id){
        pacienteRepository.deleteById(id);

    }
}
