package com.spring.social_media_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
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
