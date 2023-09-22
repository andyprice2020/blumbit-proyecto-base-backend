package com.blumbit.web.api.appgradle.service;

import com.blumbit.web.api.appgradle.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    List<Persona> getAll();
    List<Persona> getAllEnabled(boolean enabled);
    Optional<Persona> getPersonaByID(long id);
    Persona save(Persona newPersona);
    Persona update(long id, Persona updPersona);
    Persona delete(long id);
}
