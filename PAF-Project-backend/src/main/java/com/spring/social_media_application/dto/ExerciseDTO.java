package com.spring.social_media_application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExerciseDTO {
    private String exerciseId;
    private String name;
    private Integer sets;
    private Integer repetitions;
}
