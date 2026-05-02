package com.example.Ficha.medica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Ficha.medica.model.FichaMedica;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica,String> {

}
