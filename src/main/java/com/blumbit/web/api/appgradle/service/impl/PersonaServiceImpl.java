package com.blumbit.web.api.appgradle.service.impl;

import com.blumbit.web.api.appgradle.entity.Persona;
import com.blumbit.web.api.appgradle.repository.PersonaRepository;
import com.blumbit.web.api.appgradle.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //REEMPLAZA A @AUTOWIRED
@Service
public class PersonaServiceImpl implements IPersonaService {

    private final PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> getAll() {
        return personaRepository.findAll(); // EQUIVALE EN SQL A select * from table
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> getAllEnabled(boolean enabled) {
        return personaRepository.findAllByEnabled(enabled);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaByID(long id) {
        return personaRepository.findById(id);
    }

    @Override
    @Transactional
    public Persona save(Persona newPersona) {
        return personaRepository.save(newPersona); //EQUIVALE A INSERT INTO TABLE() VALUES();
    }

    @Override
    @Transactional
    public Persona update(long id, Persona updPersona) {

        // OBTENENOS EL REGISTRO A ACTUALIZAR
        var updatedPersona = personaRepository.findById(id);

        if (updatedPersona.isPresent()) {
            updatedPersona.get().setFirstName(updPersona.getFirstName());
            updatedPersona.get().setLastName(updPersona.getLastName());
            updatedPersona.get().setEnabled(updPersona.isEnabled());
        }
        return personaRepository.save(updatedPersona.get());
    }

    @Override
    @Transactional
    public Persona delete(long id) {

        var deletedPersona = personaRepository.findById(id);
        deletedPersona.ifPresent(delPersona->deletedPersona.get().setEnabled(false));
        return personaRepository.save(deletedPersona.get());
    }
}
