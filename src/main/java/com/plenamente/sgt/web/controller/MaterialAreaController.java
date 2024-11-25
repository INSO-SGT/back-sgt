package com.plenamente.sgt.web.controller;

import com.plenamente.sgt.domain.dto.MaterialArea.SearchInterventionArea;
import com.plenamente.sgt.domain.dto.MaterialArea.SearchMaterialArea;
import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialArea;
import com.plenamente.sgt.service.MaterialAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material-areas")
@CrossOrigin("")
@RequiredArgsConstructor
public class MaterialAreaController {

    private final MaterialAreaService materialAreaService;

    @PostMapping("/register")
    public ResponseEntity<MaterialArea> createMaterialArea(@RequestBody String interventionAreaName) {
        MaterialArea materialArea = materialAreaService.createAreaForMaterial(interventionAreaName);
        return new ResponseEntity<>(materialArea, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialArea> updateMaterialArea(@PathVariable Long id,@RequestParam Long interventionAreaId) {
        materialAreaService.updateMaterialArea(id,interventionAreaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<MaterialArea> assignMaterialToRoom(@RequestParam String materialId, @RequestParam Long AreaMaterialId) {
        MaterialArea materialArea = materialAreaService.assignMaterialToAreaMaterial(materialId, AreaMaterialId);
        return ResponseEntity.ok(materialArea);
    }

    @DeleteMapping("/unassign")
    public ResponseEntity<MaterialArea> unassignMaterialFromRoom(@RequestParam Long AreaMaterialId) {
        MaterialArea materialArea = materialAreaService.unassignMaterialFromAreaMaterial(AreaMaterialId);
        return ResponseEntity.ok(materialArea);
    }
}