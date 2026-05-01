package com.example.Medico.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Medico.model.Medico;
import com.example.Medico.repository.MedicoRepository;

public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public Medico findById (String id){
        return medicoRepository.findById(id).get();
    }

    public Medico save (Medico medico){
        return medicoRepository.save(medico);
    }

    public void delete(String id){
        medicoRepository.deleteById(id);
    }
}
