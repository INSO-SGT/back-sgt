package com.plenamente.sgt.domain.dto.PatientDto;

import java.util.List;

public record ListPatient(
        Long idPatient,
        String name,
        String paternalSurname,
        String maternalSurname,
        int age,
        String planName,  // Simplificación para mostrar el nombre del plan
        List<String> tutorNames,  // Lista de nombres de tutores
        boolean status,
        String photoUrl  // URL de la foto del paciente
) {}
