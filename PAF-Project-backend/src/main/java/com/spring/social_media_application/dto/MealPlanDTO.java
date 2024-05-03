package com.spring.social_media_application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MealPlanDTO {
    private String mealPlanId;
    private String userId;
    private String name;
    private String description;
    private List<RecipeDTO> recipes;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
