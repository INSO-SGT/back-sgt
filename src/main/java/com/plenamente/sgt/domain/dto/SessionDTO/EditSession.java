package com.plenamente.sgt.domain.dto.SessionDTO;

import jakarta.validation.constraints.NotNull;

public record EditSession(
        boolean state,
        String reason,
        boolean reprogrammed,
        boolean therapistPresent,
        Long idTherapist,
        Long idRoom
) {
}