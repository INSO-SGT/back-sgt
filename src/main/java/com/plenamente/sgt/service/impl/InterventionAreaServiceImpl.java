package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.service.InterventionAreaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterventionAreaServiceImpl implements InterventionAreaService {

    private final InterventionAreaRepository interventionAreaRepository;

    @Override
    public InterventionArea createAreaForIntervention(String name, String description) {
        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setName(name);
        interventionArea.setDescription(description);
        return interventionAreaRepository.save(interventionArea);
    }

    @Override
    public List<InterventionArea> getAllInterventionAreas() {
        return interventionAreaRepository.findAll();
    }

    @Override
    public InterventionArea updateInterventionArea(Long Id,@RequestBody CreateAreaForIntervention interventionArea) {
        InterventionArea existingInterventionArea = interventionAreaRepository.findById(Id)
                .orElseThrow(() -> new EntityNotFoundException("Área de intervención no encontrada con id: " + Id));;
        existingInterventionArea.setName(interventionArea.name());
        existingInterventionArea.setDescription(interventionArea.description());
        return interventionAreaRepository.save(existingInterventionArea);
    }

}