package com.blumbit.web.api.appgradle.repository;

import com.blumbit.web.api.appgradle.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    // OBTENER LOS REGISTROS HABILITADOS
    List<Persona> findAllByEnabled(boolean enabled);
}
