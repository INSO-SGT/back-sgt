package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InterventionAreaService {
    InterventionArea createAreaForIntervention(String name, String description);

    List<InterventionArea> getAllInterventionAreas();

    InterventionArea updateInterventionArea(Long Id, CreateAreaForIntervention interventionArea);
}