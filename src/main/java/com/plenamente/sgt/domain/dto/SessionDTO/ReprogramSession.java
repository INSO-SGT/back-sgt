package com.plenamente.sgt.domain.dto.SessionDTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReprogramSession(
    @FutureOrPresent(message = "No se puede colocar una fecha u hora anteriores a la presente.")
    LocalDateTime newDate,
    @NotNull(message = "La duration de la session no puede estar vacía.")
    @Min(value = 3600,message = "Las sesiones no pueden durar menos de 1 hora.")
    Long durationSeconds,
    @NotNull(message = "El ID del terapeuta no puede estar vacío.")
    Long idTherapist,
    @NotNull(message = "El ID de la sala no puede estar vacío.")
    Long idRoom
){
}
