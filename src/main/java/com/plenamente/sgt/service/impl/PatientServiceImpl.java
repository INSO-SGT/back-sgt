package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.PatientDto.RegisterPatient;
import com.plenamente.sgt.domain.dto.PatientDto.UpdatePatient;
import com.plenamente.sgt.domain.dto.PatientDto.ListPatient;
import com.plenamente.sgt.domain.entity.Patient;
import com.plenamente.sgt.domain.entity.Plan;
import com.plenamente.sgt.domain.entity.Tutor;
import com.plenamente.sgt.infra.repository.PatientRepository;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient createPatient(RegisterPatient registerPatient) {
        // Validación del apoderado
        if (registerPatient.tutors().isEmpty() || registerPatient.tutors().get(0) == null) {
            throw new IllegalArgumentException("El campo apoderado 1 es obligatorio.");
        }

        // Validación de los días según el plan
        Plan plan = registerPatient.idPlan(); // Asumiendo que Plan tiene un metodo para obtener días
        // Aqui puedes poner la validacion que gustes cuando hagas el dto de plan y todo eso ps xd

        // Crear paciente
        Patient patient = new Patient();
        patient.setName(registerPatient.name());
        patient.setPaternalSurname(registerPatient.paternalSurname());
        patient.setMaternalSurname(registerPatient.maternalSurname());
        patient.setBirthdate(registerPatient.birthdate());
        patient.setAge(registerPatient.age());
        patient.setAllergies(registerPatient.allergies());
        patient.setIdPlan(registerPatient.idPlan());
        patient.setTutors(registerPatient.tutors());

        // Manejo de la foto
        MultipartFile photoFile = registerPatient.photo();
        if (photoFile != null && !photoFile.isEmpty()) {
            String photoUrl = savePhoto(photoFile); // POR IMPLEMENTAR
            patient.setPhotoUrl(photoUrl);
        }

        return patientRepository.save(patient);
    }

    @Override
    public List<ListPatient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        // Obtener cantidad de pacientes
        int patientCount = patients.size();
        System.out.println("Total de pacientes: " + patientCount);

        // Mapear a ListPatient
        return patients.stream().map(this::mapToListPatient).collect(Collectors.toList());
    }

    @Override
    public ListPatient getPatientById(Long id) {
        // Obtener paciente por ID
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Paciente no encontrado.")
        );
        return mapToListPatient(patient);
    }

    @Override
    public Patient updatePatient(Long id, UpdatePatient updatePatient) {
        // Obtener paciente existente
        Patient existingPatient = patientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Paciente no encontrado.")
        );

        // Actualizar información del paciente
        existingPatient.setName(updatePatient.name());
        existingPatient.setPaternalSurname(updatePatient.paternalSurname());
        existingPatient.setMaternalSurname(updatePatient.maternalSurname());
        existingPatient.setBirthdate(updatePatient.birthdate());
        existingPatient.setAge(updatePatient.age());
        existingPatient.setAllergies(updatePatient.allergies());
        existingPatient.setIdPlan(updatePatient.idPlan());
        existingPatient.setTutors(updatePatient.tutors());

        // Manejo de la foto si se proporciona una nueva
        MultipartFile photoFile = updatePatient.photo();
        if (photoFile != null && !photoFile.isEmpty()) {
            String photoUrl = savePhoto(photoFile); // POR IMPLEMENTAR
            existingPatient.setPhotoUrl(photoUrl);
        }

        return patientRepository.save(existingPatient);
    }

    @Override
    public List<ListPatient> filterPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::mapToListPatient).collect(Collectors.toList());
    }

    @Override
    public List<ListPatient> filterPatientsByPlan(Long planId) {
        return patientRepository.findByIdPlanIdPlan(planId)
                .stream().map(this::mapToListPatient).collect(Collectors.toList());
    }

    @Override
    public List<ListPatient> orderPatientsByName(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return patientRepository.findAllByOrderByNameAsc()
                    .stream().map(this::mapToListPatient).collect(Collectors.toList());
        } else {
            return patientRepository.findAllByOrderByNameDesc()
                    .stream().map(this::mapToListPatient).collect(Collectors.toList());
        }
    }

    private ListPatient mapToListPatient(Patient patient) {
        return new ListPatient(
                patient.getIdPatient(),
                patient.getName(),
                patient.getPaternalSurname(),
                patient.getMaternalSurname(),
                patient.getAge(),
                patient.getIdPlan().getName(), // Le agregue un nombre a plan para diferenciar, luego lo cambias si gustas
                patient.getTutors().stream().map(Tutor::getFullName).collect(Collectors.toList()),
                patient.isStatus(),
                patient.getPhotoUrl() // POR IMPLEMENTAR
        );
    }

    private String savePhoto(MultipartFile photoFile) {
        // POR IMPLEMENTAR
        return "url_de_la_foto";
    }
}
