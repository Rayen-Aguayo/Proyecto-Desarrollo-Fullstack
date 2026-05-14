package com.example.Facturacion.y.Presupuesto.Service;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

import org.springframework.stereotype.Service;

import com.example.Facturacion.y.Presupuesto.repository.FacturacionYPresupuestoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacturacionYPresupuestoService {

    private final FacturacionYPresupuestoRepository repository;
    private final AutorClient autorClient;

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
