package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.MedicalHistoryDto.RegisterMedicalHistory;
import com.plenamente.sgt.domain.dto.ReportDto.RegisterReport;
import com.plenamente.sgt.domain.entity.MedicalHistory;
import com.plenamente.sgt.domain.entity.Report;
import com.plenamente.sgt.service.MedicalHistoryService;
import com.plenamente.sgt.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/register")
    public ResponseEntity<Report> registerReport(@RequestBody @Valid RegisterReport report) {
        Report newReport = reportService.createReport(report);
        return new ResponseEntity<>(newReport, HttpStatus.CREATED);
    }
}
