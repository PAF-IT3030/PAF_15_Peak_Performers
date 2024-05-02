package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.MealPlanDTO;
import com.spring.social_media_application.dto.RecipeDTO;
import com.spring.social_media_application.entity.MealPlan;
import com.spring.social_media_application.entity.Recipe;
import com.spring.social_media_application.entity.authentication.User;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import com.spring.social_media_application.repository.RecipeRepository;
import com.spring.social_media_application.repository.authentication.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealPlanMapper {
    private final UserRepository userRepository;
    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    public MealPlan dtoToDomain(MealPlanDTO dto, MealPlan domain) {
        if (dto == null) {
            throw new ReferenceNotFoundException("The MealPlanDTO should not be null");
        }
        User user = userRepository.findUserByUserIdIgnoreCase(dto.getUserId()).orElse(new User());
        domain.setUser(user);
        domain.setName(dto.getName());
        domain.setDescription(dto.getDescription());
        domain.setCreatedDate(dto.getCreatedDate());
        domain.setLastUpdatedDate(dto.getLastUpdatedDate());
        List<Recipe> recipeList = new ArrayList<>();
        dto.getRecipes().forEach(recipeDTO -> recipeList.add(recipeMapper.dtoToDomain(recipeDTO, new Recipe())));
        recipeList.forEach(recipe -> recipe.setUserId(user.getUserId()));
        List<Recipe> savedRecipeList = recipeRepository.saveAll(recipeList);
        domain.setRecipes(savedRecipeList);
        return domain;
    }

    public MealPlanDTO domainToDto(MealPlan domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The MealPlan should not be null");
        }
        MealPlanDTO dto = new MealPlanDTO();
        dto.setMealPlanId(domain.getMealPlanId());
        dto.setUserId(domain.getUser().getUserId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setCreatedDate(domain.getCreatedDate());
        dto.setLastUpdatedDate(domain.getLastUpdatedDate());
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        domain.getRecipes().forEach(recipe -> recipeDTOList.add(recipeMapper.domainToDto(recipe)));
        dto.setRecipes(recipeDTOList);
        return dto;
    }
}
