package com.plenamente.sgt.service;

import com.plenamente.sgt.domain.dto.PlanDto.PlanDto;
import com.plenamente.sgt.domain.entity.Plan;

import java.util.List;

public interface PlanService {
    Plan addPlan(PlanDto planDto);
    //Plan updatePlan(Long id, PlanDto planDto);
}
