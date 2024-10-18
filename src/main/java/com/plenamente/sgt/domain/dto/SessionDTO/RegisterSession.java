package com.plenamente.sgt.domain.dto.SessionDTO;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.Duration;
import java.time.LocalDateTime;

public record RegisterSession(

    @NotNull(message = "La duration de la session no puede estar vacía.")
    Duration durationHours,
    @NotNull(message = "No se dejar vacío este campo.")
    LocalDateTime assignedDate,
    @NotNull(message = "El ID del paciente no puede estar vacío.")
    Long idPatient,
    @NotNull(message = "El ID del terapeuta no puede estar vacío.")
    Long idTherapist,
    @NotNull(message = "El ID de la sala no puede estar vacío.")
    Long idRoom
){
}