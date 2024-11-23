package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.entity.InterventionArea;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.domain.entity.Room;
import com.plenamente.sgt.infra.exception.ResourceNotFoundException;
import com.plenamente.sgt.infra.repository.InterventionAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialAreaRepository;
import com.plenamente.sgt.infra.repository.MaterialRepository;
import com.plenamente.sgt.service.MaterialAreaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MaterialAreaServiceImpl implements MaterialAreaService {

    private final MaterialAreaRepository materialAreaRepository;
    private final InterventionAreaRepository interventionAreaRepository;
    private final MaterialRepository materialRepository;

    @Override
    public MaterialArea createAreaForMaterial(String interventionAreaName) {
        InterventionArea interventionArea = interventionAreaRepository.findByName(interventionAreaName)
                .orElseThrow(() -> new ResourceNotFoundException("Área de intervención no encontrada con nombre: " + interventionAreaName));

        MaterialArea materialArea = new MaterialArea();
        materialArea.setInterventionArea(interventionArea);

        return materialAreaRepository.save(materialArea);
    }

    @Override
    public MaterialArea updateMaterialArea(Long id, Long interventionAreaId) {
        MaterialArea materialArea = materialAreaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área de material no encontrada con id: " + id));

        InterventionArea interventionArea = interventionAreaRepository.findById(interventionAreaId)
                .orElseThrow(() -> new ResourceNotFoundException("Área de intervención no encontrada con nombre: " + interventionAreaId));

        materialArea.setInterventionArea(interventionArea);
        return materialAreaRepository.save(materialArea);
    }

    @Override
    public MaterialArea assignMaterialToAreaMaterial(String materialId, Long AreaMaterialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + materialId));

        MaterialArea materialArea = materialAreaRepository.findById(AreaMaterialId)
                .orElseThrow(() -> new EntityNotFoundException("Ambiente no encontrado con id: " + AreaMaterialId));

        materialArea.setMaterial(material);
        return materialAreaRepository.save(materialArea);
    }

    @Override
    public MaterialArea unassignMaterialFromAreaMaterial(Long AreaMaterialId) {
        MaterialArea materialArea = materialAreaRepository.findById(AreaMaterialId)
                .orElseThrow(() -> new EntityNotFoundException("Ambiente no encontrado con id: " + AreaMaterialId));

        materialArea.setMaterial(null);
        return materialAreaRepository.save(materialArea);
    }

    @Override
    public List<MaterialArea> getMaterialsByAreaMaterial(String materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new EntityNotFoundException("Material no encontrado con id: " + materialId));

        return materialAreaRepository.findByMaterial(material);
    }
}