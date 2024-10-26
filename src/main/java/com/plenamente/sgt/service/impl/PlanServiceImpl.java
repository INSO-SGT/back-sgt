package com.plenamente.sgt.service.impl;

import com.plenamente.sgt.domain.dto.PlanDto.PlanDto;
import com.plenamente.sgt.domain.entity.Plan;
import com.plenamente.sgt.infra.repository.PlanRepository;
import com.plenamente.sgt.service.PlanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public Plan addPlan(PlanDto planDto) {
        Plan plan  = new Plan();

        //Asignar los datos a la entidad plan
        plan.setNumOfSessions(planDto.numOfSessions());
        plan.setCost(planDto.cost());

        return planRepository.save(plan); //Guardar en la base de datos
    }
    /*@Override
    public Plan updatePlan(Long id, PlanDto planDto) {
        // Buscar el plan
        Plan plan = planRepository.findById(id)
                .orElseThrow(()  -> new EntityNotFoundException("Plan no encontrado con id: " + id));
        // Actualizar la informacion del plan
        plan.setNumOfSessions(planDto.numOfSessions());
        plan.setCost(planDto.cost());
        return planRepository.save(plan);
    }*/
}
