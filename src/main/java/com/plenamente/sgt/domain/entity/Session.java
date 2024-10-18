package com.plenamente.sgt.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Session")
@Table(name = "sessions")
@Setter
@Getter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;
    private Duration durationHours;
    private LocalDateTime assignedDate;
    private Integer numberSession;
    private boolean state;
    private String reason;
    private boolean reprogrammed;
    private LocalDateTime newDate;
    private boolean therapistPresent;

    @ManyToOne
    @JoinColumn(name="idPatient", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="idTherapist", nullable = true)
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name="idRoom", nullable = true)
    private Room room;
}