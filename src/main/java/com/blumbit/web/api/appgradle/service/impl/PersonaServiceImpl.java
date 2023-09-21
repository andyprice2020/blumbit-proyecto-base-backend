package com.blumbit.web.api.appgradle.service.impl;

import com.blumbit.web.api.appgradle.entity.Persona;
import com.blumbit.web.api.appgradle.repository.PersonaRepository;
import com.blumbit.web.api.appgradle.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //REEMPLAZA A @AUTOWIRED
@Service
public class PersonaServiceImpl implements IPersonaService {

    private final PersonaRepository personaRepository;

    @Override
    public List<Persona> getAll() {
        return personaRepository.findAll(); // EQUIVALE EN SQL A select * from table
    }

    @Override
    public Persona save(Persona newPersona) {
        return personaRepository.save(newPersona); //EQUIVALE A INSERT INTO TABLE() VALUES();
    }
}
