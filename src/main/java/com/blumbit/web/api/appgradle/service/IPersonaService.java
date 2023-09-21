package com.blumbit.web.api.appgradle.service;

import com.blumbit.web.api.appgradle.entity.Persona;

import java.util.List;

public interface IPersonaService {
    List<Persona> getAll();
    Persona save(Persona newPersona);
}
