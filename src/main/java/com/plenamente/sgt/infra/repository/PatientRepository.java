package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
