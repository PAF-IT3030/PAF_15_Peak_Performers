package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.RoutineDTO;
import com.spring.social_media_application.dto.WorkoutPlanDTO;
import com.spring.social_media_application.entity.Routine;
import com.spring.social_media_application.entity.WorkoutPlan;
import com.spring.social_media_application.entity.authentication.User;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import com.spring.social_media_application.repository.RoutineRepository;
import com.spring.social_media_application.repository.authentication.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkoutPlanMapper {
    private final UserRepository userRepository;
    private final RoutineMapper routineMapper;
    private final RoutineRepository routineRepository;

    public WorkoutPlan dtoToDomain(WorkoutPlanDTO dto, WorkoutPlan domain) {
        if (dto == null) {
            throw new ReferenceNotFoundException("The WorkoutPlanDTO should not be null");
        }
        User user = userRepository.findUserByUserIdIgnoreCase(dto.getUserId()).orElse(new User());
        domain.setUser(user);
        domain.setName(dto.getName());
        domain.setDescription(dto.getDescription());
        domain.setCreatedDate(dto.getCreatedDate());
        domain.setUpdatedDate(dto.getUpdatedDate());
        List<Routine> routineList = new ArrayList<>();
        dto.getRoutineDTOS().forEach(routineDTO -> routineList.add(routineMapper.dtoToDomain(routineDTO, new Routine())));
        routineList.forEach(routine -> routine.setUserId(user.getUserId()));
        List<Routine> savedRoutineList = routineRepository.saveAll(routineList);
        domain.setRoutines(savedRoutineList);
        return domain;
    }

    public WorkoutPlanDTO domainToDto(WorkoutPlan domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The WorkoutPlan should not be null");
        }
        WorkoutPlanDTO dto = new WorkoutPlanDTO();
        dto.setPlanId(domain.getId());
        dto.setUserId(domain.getUser().getUserId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setCreatedDate(domain.getCreatedDate());
        dto.setUpdatedDate(domain.getUpdatedDate());
        List<RoutineDTO> routineDTOList = new ArrayList<>();
        domain.getRoutines().forEach(routine -> routineDTOList.add(routineMapper.domainToDto(routine)));
        dto.setRoutineDTOS(routineDTOList);
        return dto;
    }
}
