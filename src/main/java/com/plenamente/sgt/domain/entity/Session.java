package com.plenamente.sgt.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
public class Session {
    @Id
    private Long idSession;
    private Duration duration; // Aqui use duration en vez de Time, pq Time es para una hora especifica del dia, y Duration es para un intervalo de tiempo.


}