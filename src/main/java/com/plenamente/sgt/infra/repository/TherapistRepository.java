package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TherapistRepository extends JpaRepository<Therapist, Long> {
    Optional<Therapist> findByIdUser(Long id);
}
