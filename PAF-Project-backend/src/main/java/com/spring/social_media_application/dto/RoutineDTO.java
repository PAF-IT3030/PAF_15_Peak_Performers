package com.spring.social_media_application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RoutineDTO {
    private String routineId;
    private String userId;
    private String name;
    private List<ExerciseDTO> exerciseDTOS;
}
