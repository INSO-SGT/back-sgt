package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.InterventionAreaDto.CreateAreaForIntervention;
import com.plenamente.sgt.domain.dto.InterventionAreaDto.ListInterventionArea;
import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.mapper.InterventionAreaMapper;
import com.plenamente.sgt.service.InterventionAreaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InterventionAreaServiceImpl implements InterventionAreaService {

    private final InterventionAreaRepository interventionAreaRepository;
    private final InterventionAreaMapper interventionAreaMapper;

    @Override
    public InterventionArea createAreaForIntervention(String name, String description) {
        InterventionArea interventionArea = new InterventionArea();
        interventionArea.setName(name);
        interventionArea.setDescription(description);
        return interventionAreaRepository.save(interventionArea);
    }

    @Override
    public List<ListInterventionArea> getAllInterventionAreas() {
        List<InterventionArea> interventionAreas = interventionAreaRepository.findAll();

        for (InterventionArea area : interventionAreas) {
            Hibernate.initialize(area.getMaterialAreas());
        }
        return interventionAreas.stream()
                .map(interventionAreaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterventionArea deleteInterventionArea(Long id){
        InterventionArea ExisArea=interventionAreaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Intervention Area no encontrado con id: " + id));
        interventionAreaRepository.delete(ExisArea);
        return ExisArea;
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