package com.spring.social_media_application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class WorkoutPlanDTO {
    private String planId;
    private String userId;
    private String name;
    private String description;
    private List<RoutineDTO> routineDTOS;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
