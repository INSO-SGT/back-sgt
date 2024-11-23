package com.plenamente.sgt.infra.repository;

import com.plenamente.sgt.domain.entity.Material;
import com.plenamente.sgt.domain.entity.MaterialArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialAreaRepository extends JpaRepository<MaterialArea, Long> {
    List<MaterialArea> findByMaterial(Material material);

}
