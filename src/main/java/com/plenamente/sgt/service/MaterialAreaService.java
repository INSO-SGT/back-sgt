package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialArea;

import java.util.List;

public interface MaterialAreaService {
    MaterialArea createAreaForMaterial(String interventionAreaName);
    MaterialArea updateMaterialArea(Long id, Long interventionAreaId);
    MaterialArea assignMaterialToAreaMaterial(String materialId, Long AreaMaterialId);
    MaterialArea unassignMaterialFromAreaMaterial(Long AreaMaterialId);
    List<MaterialArea> getMaterialsByAreaMaterial(String materialId);

}