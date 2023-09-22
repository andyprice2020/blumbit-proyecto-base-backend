package com.blumbit.web.api.appgradle.controller;

import com.blumbit.web.api.appgradle.dto.PersonaDto;
import com.blumbit.web.api.appgradle.entity.Persona;
import com.blumbit.web.api.appgradle.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PersonaController {

    private final IPersonaService personaService;

    // INYECTAR MODELMAPPER PARA UTILIZAR LAS CLASES DTO
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDto>> getAllPersonas() {

        try {
            var personas = personaService.getAll().stream().map(allPersons->modelMapper.map(allPersons, PersonaDto.class)).collect(Collectors.toList());
            // USO DE SLF4J PARA DESLPIEGUE DE MENSAJES EN CONSOLA, Y POSTERIORMENTE EN UN ARCHIVO LOG
            log.debug("Listado completo de registros");
            return new ResponseEntity<>(personas, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de registros " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // LISTADO DE PERSONAS HABILITADAS
    @GetMapping("/personas/{enabled}")
    public ResponseEntity<List<PersonaDto>> getAllEnabledPersonas(@PathVariable boolean enabled) {

        try {
            var enabledPersonas = personaService.getAllEnabled(true).stream().map(allEnabPersons->modelMapper.map(allEnabPersons, PersonaDto.class)).collect(Collectors.toList());
            // USO DE SLF4J PARA DESLPIEGUE DE MENSAJES EN CONSOLA, Y POSTERIORMENTE EN UN ARCHIVO LOG
            log.debug("Listado de registros habilitados");
            return new ResponseEntity<>(enabledPersonas, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de registros habilitados " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/single-personas/{id}")
    public ResponseEntity<PersonaDto> getPersonaByID(@PathVariable long id) {

        try{
            var singlePersona = personaService.getPersonaByID(id);
            var responsePersona = modelMapper.map(singlePersona.get(), PersonaDto.class);

            if (responsePersona != null) {
                log.debug("Un registro");
                return new ResponseEntity<>(responsePersona, HttpStatus.OK);
            }
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener un registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/personas")
    public ResponseEntity<PersonaDto> savePersona(@RequestBody PersonaDto newPersonaDto) {

        try {
            var personaRequest = modelMapper.map(newPersonaDto, Persona.class);
            var savedPersona = personaService.save(personaRequest);
            var responsePersona = modelMapper.map(savedPersona, PersonaDto.class);
            log.debug("Registro guardado exitosamente!");
            return new ResponseEntity<>(responsePersona, HttpStatus.CREATED);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener un registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable long id, @RequestBody PersonaDto updPersonaDto) {

        try {
            var personaRequest = modelMapper.map(updPersonaDto, Persona.class);
            var updatedPersona = personaService.update(id, personaRequest);
            var responsePersona = modelMapper.map(updatedPersona, PersonaDto.class);
            log.debug("Registro actualizado exitosamente!");
            return new ResponseEntity<>(responsePersona, HttpStatus.ACCEPTED);
        }
        catch (DataAccessException daex) {
            log.error("Error al actualizar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/personas/{id}")
    public ResponseEntity<PersonaDto> deletePersonaByID(@PathVariable long id){

        try {
            var deletedPersona = personaService.delete(id);
            var responsePersona = modelMapper.map(deletedPersona, PersonaDto.class);
            log.debug("Registro eliminado");
            return new ResponseEntity<>(responsePersona, HttpStatus.NO_CONTENT);
        }
        catch (DataAccessException daex) {
            log.error("Error al actualizar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
