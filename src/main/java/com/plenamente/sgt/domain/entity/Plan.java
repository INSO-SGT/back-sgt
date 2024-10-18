package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Plans")
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlan;
    private String name;
    private int numOfSessions;
    private double cost;
}
