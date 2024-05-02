package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.ExerciseDTO;
import com.spring.social_media_application.entity.Exercise;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseMapper {
    public Exercise dtoToDomain(ExerciseDTO dto, Exercise domain) {
        if (dto == null) {
            throw new ReferenceNotFoundException("The ExerciseDTO should not be null");
        }
        domain.setName(dto.getName());
        domain.setSets(dto.getSets());
        domain.setRepetitions(dto.getRepetitions());
        return domain;
    }

    public ExerciseDTO domainToDto(Exercise domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The Exercise should not be null");
        }
        ExerciseDTO dto = new ExerciseDTO();
        dto.setExerciseId(domain.getId());
        dto.setName(domain.getName());
        dto.setSets(domain.getSets());
        dto.setRepetitions(domain.getRepetitions());
        return dto;
    }
}
