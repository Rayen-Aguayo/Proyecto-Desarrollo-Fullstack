package com.example.Receta.Medica.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Receta.Medica.repository.RecetaMedicaRepository;

public class RecetaMedicaService {

    @Autowired
    private RecetaMedicaRepository recetaMedicaRepository;
    
    public LibroResponse  crear(LibroDTO dto, String token) {

        log.info("Crear libro", keyValue("titulo", dto.getTitulo()));

        var autor = autorClient.obtenerAutor(dto.getAutorId(), token);

        if (autor == null) {
            throw new RuntimeException("Autor no existe");
        }

        Libro libro = repo.save(
                new Libro(null, dto.getTitulo(), dto.getAnio(), dto.getAutorId())
        );

        return mapToResponse(libro, token);
    }

    public List<LibroResponse> listar(String token) {

        return repo.findAll()
                .stream()
                .map(l -> mapToResponse(l, token))
                .toList();
    }

    public LibroResponse obtener(Long id, String token) {

        Libro libro = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));

        return mapToResponse(libro, token);
    }

    public LibroResponse actualizar(Long id, LibroDTO dto, String token) {

        var autor = autorClient.obtenerAutor(dto.getAutorId(), token);

        if (autor == null) {
            throw new RuntimeException("Autor no existe");
        }

        Libro l = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Libro no encontrado"));

        l.setTitulo(dto.getTitulo());
        l.setAnio(dto.getAnio());
        l.setAutorId(dto.getAutorId());

        return mapToResponse(repo.save(l), token);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    private LibroResponse mapToResponse(Libro libro, String token) {

        var autor = autorClient.obtenerAutor(libro.getAutorId(), token);

        return LibroResponse.builder()
                .id(libro.getId())
                .titulo(libro.getTitulo())
                .anio(libro.getAnio())
                .autor(autor)
                .build();
    }
}