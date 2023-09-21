package com.blumbit.web.api.appgradle.repository;

import com.blumbit.web.api.appgradle.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
