package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name="Patient")
@Table(name="patients")
@Setter
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;
    private String name;
    private String dni;
    private String paternalSurname;
    private String maternalSurname;
    private LocalDate birthDay;
    private Integer age;
    private String allergies;
    private String educationalInstitution;
    private boolean state;

    @ManyToOne
    @JoinColumn(name="idPlan", nullable = false)
    private Plan plan;
}
