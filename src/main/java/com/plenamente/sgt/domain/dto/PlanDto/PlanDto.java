package com.plenamente.sgt.domain.dto.PlanDto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanDto (
        @NotNull Integer numOfSessions,
        @NotNull @Digits(integer = 10, fraction = 2) Double cost  // Validacion para utilizar 2 decimales
){
}
