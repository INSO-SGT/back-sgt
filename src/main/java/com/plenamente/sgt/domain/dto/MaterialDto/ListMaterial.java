package com.plenamente.sgt.domain.dto.MaterialDto;

import com.plenamente.sgt.domain.entity.MaterialStatus;

public record ListMaterial(
        String idMaterial,
        String nombre,
        String descripcion,
        int stock,
        MaterialStatus estado,
        Long idRoom
) {
}
