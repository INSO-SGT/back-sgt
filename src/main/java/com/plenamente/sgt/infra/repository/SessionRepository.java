package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findBySessionDate(LocalDate date);
    List<Session> findByTherapist_IdUser(Long therapistId);
    List<Session> findByRoom_IdRoom(Long idRoom);
}
