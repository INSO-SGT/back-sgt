package com.plenamente.sgt.domain.dto.SessionDTO;

import com.plenamente.sgt.domain.entity.Patient;

import java.time.Duration;
import java.time.LocalDateTime;

public record DetailsSession(
        String patientName,
        String therapistName,
        String roomName,
        String roomAddress,
        Duration durationHours,
        LocalDateTime assignedDate,
        Integer numberSession,
        boolean state,
        boolean reprogrammed,
        LocalDateTime newDate
) {
}
