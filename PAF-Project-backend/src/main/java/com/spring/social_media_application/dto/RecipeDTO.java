package com.spring.social_media_application.dto;

import com.spring.social_media_application.entity.Ingredient;
import com.spring.social_media_application.entity.Nutrition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RecipeDTO {
    private String recipeId;
    private String userId;
    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
    private String photoUrl;
    private Nutrition nutrition;
    private String portionSize;
    private List<String> dietaryPreferences;
}
