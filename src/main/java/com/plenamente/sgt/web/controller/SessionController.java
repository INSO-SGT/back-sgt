package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.SessionDTO.DetailsSession;
import com.plenamente.sgt.domain.dto.SessionDTO.RegisterSession;
import com.plenamente.sgt.domain.entity.Session;
import com.plenamente.sgt.service.SesionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")

public class SessionController {

    private final SesionService sesionService;

    @Autowired  // Esta anotación es clave
    public SessionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    // Metodo para registrar una nueva sesion
    @PostMapping("/register")
    public ResponseEntity<String> registerSession(@Valid @RequestBody RegisterSession registerSession) {
        String responseMessage = sesionService.registerSession(registerSession);
        return ResponseEntity.ok(responseMessage); // Devuelve el mensaje de éxito, comprobado
    }

    // Metodo para obtener todas las sesiones
    @GetMapping("/show")
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sesionService.getAllSessions();
        return ResponseEntity.ok(sessions); // Devuelve la lista de sesiones con un status 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsSession> getSessionDetails(@PathVariable Long id) {
        DetailsSession detailsSession = sesionService.getSessionDetails(id);
        return ResponseEntity.ok(detailsSession); // Devuelve los detalles de la sesión con un status 200 OK
    }
}
