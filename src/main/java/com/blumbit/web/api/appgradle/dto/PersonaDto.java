package com.blumbit.web.api.appgradle.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonaDto {

    private long id;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private LocalDateTime created;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime modified;
    private long countModified;
}
