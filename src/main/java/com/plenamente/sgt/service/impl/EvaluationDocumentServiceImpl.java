package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.EvaluationDocumentDto.RegisterEvaluationDocument;
import com.plenamente.sgt.domain.entity.EvaluationDocument;
import com.plenamente.sgt.domain.entity.MedicalHistory;
import com.plenamente.sgt.infra.repository.EvaluationDocumentRepository;
import com.plenamente.sgt.infra.repository.MedicalHistoryRepository;
import com.plenamente.sgt.service.EvaluationDocumentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class EvaluationDocumentServiceImpl implements EvaluationDocumentService {
    @Autowired
    private EvaluationDocumentRepository evaluationDocumentRepository;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public EvaluationDocument createEvaluationDocument(RegisterEvaluationDocument evaluationDocument, MultipartFile file){
        EvaluationDocument newEvaluationDocument = new EvaluationDocument();
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(evaluationDocument.idMedicalHistory())
                .orElseThrow(()-> new EntityNotFoundException("Historial medico no encontrado con id: " + evaluationDocument.idMedicalHistory()));
        newEvaluationDocument.setMedicalHistory(medicalHistory);
        newEvaluationDocument.setName(evaluationDocument.name());
        newEvaluationDocument.setDescription(evaluationDocument.description());
        newEvaluationDocument.setDocumentType(evaluationDocument.documentType());
        if (file != null && !file.isEmpty()) {
            try {
                newEvaluationDocument.setDocumentType(file.getContentType());
                byte[] archive = file.getBytes();
                newEvaluationDocument.setArchive(archive);
            } catch (IOException e) {
                throw new RuntimeException("Error al almacenar el archivo: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("El archivo no puede estar vacío.");
        }

        return evaluationDocumentRepository.save(newEvaluationDocument);
    }
    @Override
    public EvaluationDocument downloadEvaluationDocument(Long id) {
        return evaluationDocumentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Historial medico no encontrado con id: " + id));
    }
}