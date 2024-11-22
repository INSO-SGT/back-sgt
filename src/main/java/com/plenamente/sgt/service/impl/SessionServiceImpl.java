package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.SessionDto.ListSession;
import com.plenamente.sgt.domain.dto.SessionDto.RegisterSession;
import com.plenamente.sgt.domain.dto.SessionDto.UpdateSession;
import com.plenamente.sgt.domain.entity.*;
import com.plenamente.sgt.infra.repository.*;
import com.plenamente.sgt.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final PlanRepository planRepository;

    @Override
    public Session createSession(RegisterSession dto) {
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
        Therapist therapist = (Therapist) userRepository.findById(dto.therapistId())
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));
        Room room = roomRepository.findById(dto.roomId())
                .orElseThrow(() -> new EntityNotFoundException("Sala no encontrada"));
        Plan plan = planRepository.findById(dto.planId())
                .orElseThrow(() -> new EntityNotFoundException("Plan no encontrado"));

        Session session = new Session();
        session.setSessionDate(dto.sessionDate());
        session.setStartTime(dto.startTime());
        session.setEndTime(dto.endTime());
        session.setPatient(patient);
        session.setTherapist(therapist);
        session.setRoom(room);
        session.setPlan(plan);

        return sessionRepository.save(session);
    }

    @Override
    public List<ListSession> getSessionsByTherapist(Long therapistId) {
        // Verificar que el terapeuta existe
        Therapist therapist = (Therapist) userRepository.findById(therapistId)
                .orElseThrow(() -> new EntityNotFoundException("Terapeuta no encontrado"));

        // Consultar las sesiones asignadas al terapeuta
        return sessionRepository.findByTherapist_IdUser(therapistId)
                .stream()
                .map(session -> new ListSession(
                        session.getIdSession(),
                        session.getSessionDate(),
                        session.getStartTime(),
                        session.getEndTime(),
                        session.getPatient().getName(),
                        session.getTherapist().getName(),
                        session.getRoom().getName(),
                        session.isRescheduled()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<ListSession> getSessionsByDate(LocalDate date) {
        return sessionRepository.findBySessionDate(date)
                .stream()
                .map(session -> new ListSession(
                        session.getIdSession(),
                        session.getSessionDate(),
                        session.getStartTime(),
                        session.getEndTime(),
                        session.getPatient().getName(),
                        session.getTherapist().getName(),
                        session.getRoom().getName(),
                        session.isRescheduled()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Session updateSession(UpdateSession dto) {
        Session session = sessionRepository.findById(dto.idSession())
                .orElseThrow(() -> new EntityNotFoundException("Sesión no encontrada"));

        session.setSessionDate(dto.sessionDate());
        session.setStartTime(dto.startTime());
        session.setEndTime(dto.endTime());
        session.setReason(dto.reason());
        session.setRescheduled(true);

        return sessionRepository.save(session);
    }
}
