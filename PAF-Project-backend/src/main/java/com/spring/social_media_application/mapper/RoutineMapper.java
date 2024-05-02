package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.ExerciseDTO;
import com.spring.social_media_application.dto.RoutineDTO;
import com.spring.social_media_application.entity.Exercise;
import com.spring.social_media_application.entity.Routine;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import com.spring.social_media_application.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoutineMapper {
    private final ExerciseMapper exerciseMapper;
    private final ExerciseRepository exerciseRepository;

    public Routine dtoToDomain(RoutineDTO dto, Routine domain) {
        if (dto == null) {
            throw new ReferenceNotFoundException("The RoutineDTO should not be null");
        }
        domain.setName(dto.getName());
        List<Exercise> exerciseList = new ArrayList<>();
        dto.getExerciseDTOS().forEach(exerciseDTO -> exerciseList.add(exerciseMapper.dtoToDomain(exerciseDTO, new Exercise())));
        List<Exercise> savedExerciseList = exerciseRepository.saveAll(exerciseList);
        domain.setExercises(savedExerciseList);
        return domain;
    }

    public RoutineDTO domainToDto(Routine domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The Routine should not be null");
        }
        RoutineDTO dto = new RoutineDTO();
        dto.setRoutineId(domain.getId());
        dto.setUserId(domain.getUserId());
        dto.setName(domain.getName());
        List<ExerciseDTO> exerciseDTOList = new ArrayList<>();
        domain.getExercises().forEach(exercise -> exerciseDTOList.add(exerciseMapper.domainToDto(exercise)));
        dto.setExerciseDTOS(exerciseDTOList);
        return dto;
    }
}
