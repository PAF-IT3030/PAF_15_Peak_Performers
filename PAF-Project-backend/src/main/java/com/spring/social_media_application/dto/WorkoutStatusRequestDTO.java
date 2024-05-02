package com.spring.social_media_application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutStatusRequestDTO {
    private String id;
    private String userId;
    private Double distance;
    private Integer pushUp;
    private Double weightLifted;
    private String description;
}
