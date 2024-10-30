package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.ReportDto.RegisterReport;
import com.plenamente.sgt.domain.entity.MedicalHistory;
import com.plenamente.sgt.domain.entity.Report;
import com.plenamente.sgt.infra.repository.MedicalHistoryRepository;
import com.plenamente.sgt.infra.repository.ReportRepository;
import com.plenamente.sgt.service.ReportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public Report createReport(RegisterReport report){
        Report newReport = new Report();
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(report.idMedicalHistory())
                .orElseThrow(()-> new EntityNotFoundException("Historial medico no encontrado con id: " + report.idMedicalHistory()));
        newReport.setMedicalHistory(medicalHistory);
        newReport.setName(report.name());
        newReport.setDescription(report.description());
        return reportRepository.save(newReport);
    }
}
