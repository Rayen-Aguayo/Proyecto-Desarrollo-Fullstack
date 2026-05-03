package com.example.Reservar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reservar.model.PedirHora;
import com.example.Reservar.service.PedirHoraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/recervar-y-anular-hora")
public class PedirHoraController {
    @Autowired PedirHoraService pedirHoraService;

    @GetMapping
    public ResponseEntity<List<PedirHora>> listarhoras () {
        List<PedirHora> pedirHora = pedirHoraService.findAll();
        if(pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@Valid @RequestBody Paciente paciente) {
        Paciente pacienteNuevo = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable String id) {
        try{
            Paciente paciente = pacienteService.findById(id);
            return ResponseEntity.ok(paciente);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable String id, @Valid @RequestBody Paciente paciente) {
        try{
            Paciente pac = pacienteService.findById(id);
            pac.setAlergias(paciente.getAlergias());
            pac.setDatosDelPaciente(paciente.getDatosDelPaciente());
            pac.setEnfermedad(paciente.getEnfermedad());
            pac.setEdad(paciente.getEdad());
            pac.setNroTelefono(paciente.getNroTelefono());
            pac.setQueMedicamentoEstaTomando(paciente.getQueMedicamentoEstaTomando());
            pacienteService.save(pac);
            return ResponseEntity.ok(paciente);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")

    public ResponseEntity<?> eliminar (@PathVariable String id){
        try{
            pacienteService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }
    
   
}
