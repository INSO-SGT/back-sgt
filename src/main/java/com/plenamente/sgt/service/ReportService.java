package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.ReportDto.RegisterReport;
import com.plenamente.sgt.domain.entity.Report;

public interface ReportService {
    Report createReport(RegisterReport report);
}