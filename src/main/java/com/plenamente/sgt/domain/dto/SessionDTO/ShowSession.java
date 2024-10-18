package com.plenamente.sgt.domain.dto.SessionDTO;

import com.plenamente.sgt.domain.entity.Therapist;

import java.time.LocalDateTime;

public record ShowSession(
        Long id,
        LocalDateTime assignedDate,
        Therapist idTherapist
) {
}