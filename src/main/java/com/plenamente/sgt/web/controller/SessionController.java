package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.SessionDto.ListSession;
import com.plenamente.sgt.domain.dto.SessionDto.RegisterSession;
import com.plenamente.sgt.domain.dto.SessionDto.UpdateSession;
import com.plenamente.sgt.domain.entity.Session;
import com.plenamente.sgt.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<Session> registerSession(@RequestBody RegisterSession dto) {
        return ResponseEntity.ok(sessionService.createSession(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Session> updateSession(@RequestBody UpdateSession dto) {
        return ResponseEntity.ok(sessionService.updateSession(dto));
    }

    @GetMapping("/date")
    public ResponseEntity<List<ListSession>> getSessionsByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(sessionService.getSessionsByDate(date));
    }
}