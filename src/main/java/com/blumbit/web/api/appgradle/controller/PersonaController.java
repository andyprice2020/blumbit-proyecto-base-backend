package com.blumbit.web.api.appgradle.controller;

import com.blumbit.web.api.appgradle.entity.Persona;
import com.blumbit.web.api.appgradle.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PersonaController {

    private final IPersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> getAllPersonas() {

        List<Persona> personas = new ArrayList<>(personaService.getAll());
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PostMapping("/personas")
    public ResponseEntity<Persona> savePersona(@RequestBody Persona newPersona) {
        personaService.save(newPersona);
        return new ResponseEntity<>(newPersona, HttpStatus.CREATED);
    }
}
