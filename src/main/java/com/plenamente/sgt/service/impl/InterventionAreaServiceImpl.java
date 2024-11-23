package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.service.InterventionAreaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public InterventionArea deleteInterventionArea(Long id){
        InterventionArea ExisArea=interventionAreaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Intervention Area no encontrado con id: " + id));
        interventionAreaRepository.delete(ExisArea);
        return ExisArea;
    }
}