package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.SessionDTO.DetailsSession;
import com.plenamente.sgt.domain.dto.SessionDTO.RegisterSession;
import com.plenamente.sgt.domain.entity.Patient;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.domain.entity.Session;
import com.plenamente.sgt.domain.entity.Therapist;
import com.plenamente.sgt.infra.exception.BadDateException;
import com.plenamente.sgt.infra.exception.BadDurationException;
import com.plenamente.sgt.infra.repository.SessionRepository;
import com.plenamente.sgt.infra.repository.RoomRepository;
import com.plenamente.sgt.infra.repository.PatientRepository;
import com.plenamente.sgt.infra.repository.TherapistRepository;
import com.plenamente.sgt.service.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements SesionService {

    private final SessionRepository sessionRepository; // Cambiado a final
    private final PatientRepository patientRepository; // Añadir repositorio del paciente
    private final TherapistRepository therapistRepository; // Añadir repositorio del terapeuta
    private final RoomRepository roomRepository; // Añadir repositorio de la sala

    @Override
    public String registerSession(RegisterSession registerSession) {
        // Crear una nueva instancia de Session
        Session session = new Session();
        Long patientId = registerSession.idPatient();

        // Asignar los valores del DTO a la entidad
        session.setDurationHours(registerSession.durationHours());
        session.setAssignedDate(registerSession.assignedDate());
        session.setNumberSession(calculateNextSessionNumber(patientId)); // Implementa este método según tu lógica
        session.setState(true); // O el valor que desees por defecto
        session.setReprogrammed(false); // O el valor que desees por defecto
        session.setNewDate(null); // Inicialmente nulo
        session.setTherapistPresent(false); // O el valor que desees por defecto

        if (registerSession.durationHours().toHours() < 1) {
            throw new BadDurationException("La duración de la sesión debe ser al menos 1 hora.");
        }

        if (registerSession.assignedDate().isBefore(LocalDateTime.now())) {
            throw new BadDateException("No se puede colocar una fecha u hora pasada.");
        }

        // Obtener y asignar las entidades relacionadas
        Patient patient = patientRepository.findById(registerSession.idPatient())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Therapist therapist = therapistRepository.findByIdUser(registerSession.idTherapist())
                .orElseThrow(() -> new RuntimeException("Terapeuta no encontrado"));
        Room room = roomRepository.findById(registerSession.idRoom())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        session.setPatient(patient);
        session.setTherapist(therapist);
        session.setRoom(room);

        // Guardar la sesión en la base de datos
        sessionRepository.save(session);
        return "Se ha creado la sesión con éxito";
    }

    private Integer calculateNextSessionNumber(Long patientId) {
        List<Session> existingSessions = sessionRepository.findSessionsByPatient_IdPatient(patientId);

        // Si no hay sesiones, comenzamos con 1
        if (existingSessions.isEmpty()) {
            return 1;
        }

        // Encuentra el número máximo de la sesión existente
        return existingSessions.stream()
                .mapToInt(Session::getNumberSession) // Asumiendo que tienes un metodo getNumberSession en Session
                .max()
                .orElse(0) + 1; // Si no hay max, retornamos 0 y sumamos 1
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll(); // Llama al metodo del repositorio
    }

    @Override
    public DetailsSession getSessionDetails(Long id) {
        Session session = sessionRepository.findSessionByIdSession(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada"));

        // Obtener los datos necesarios para el DTO
        String patientName = session.getPatient().getName(); // Asegúrate de que Patient tenga un método getName()
        String therapistName = session.getTherapist().getName(); // Asegúrate de que Therapist tenga un método getName()
        String roomName = session.getRoom().getName(); // Asegúrate de que Room tenga un método getName()
        String roomAddress = session.getRoom().getAddress(); // Asegúrate de que Room tenga un método getAddress()

        return new DetailsSession(
                patientName,
                therapistName,
                roomName,
                roomAddress,
                session.getDurationHours(),
                session.getAssignedDate(),
                session.getNumberSession(),
                session.isState(),
                session.isReprogrammed(),
                session.getNewDate()
        );
    }
}