package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.dto.SessionDTO.ShowSession;
import com.plenamente.sgt.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

//    List<ShowSession> getSessions();
    Optional<Session> findSessionByIdSession(Long id);
    Optional<Session> findSessionByAssignedDate(LocalDateTime assignedDate);
    List<Session> findSessionsByPatient_IdPatient(Long idPatient);
    List<Session> findAll();
}