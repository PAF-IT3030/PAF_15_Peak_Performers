package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.RecipeDTO;
import com.spring.social_media_application.entity.Recipe;
import com.spring.social_media_application.mapper.RecipeMapper;
import com.spring.social_media_application.repository.RecipeRepository;
import com.spring.social_media_application.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public CommonResponse getAllRecipeDetails() {
        log.info("RecipeServiceImpl.getAllRecipeDetails method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        List<Recipe> recipes = recipeRepository.findAll();
        recipes.forEach(workoutPlan ->  recipeDTOList.add(recipeMapper.domainToDto(workoutPlan)));
        if (recipes.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<Recipe>());
            commonResponse.setMessage("Recipe details list not available!");
            log.warn("Recipe details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Recipe details are fetching success!");
        commonResponse.setData(recipeDTOList);
        log.info("RecipeServiceImpl.getAllRecipeDetails method end");
        return commonResponse;
    }

    @Override
    public CommonResponse getRecipeDetailsById(String recipeId) {
        log.info("RecipeServiceImpl.getRecipeDetailsById method accessed");
        RecipeDTO recipeDTO;
        CommonResponse commonResponse = new CommonResponse();
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(recipe.isPresent()) {
            recipeDTO = recipeMapper.domainToDto(recipe.get());
        } else {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<>());
            commonResponse.setMessage("Recipe details is not available!");
            log.warn("Recipe details not available. message : {} ", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Recipe details is fetching success!");
        commonResponse.setData(recipeDTO);
        log.info("RecipeServiceImpl.getRecipeDetailsById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteRecipeById(String recipeId) {
        log.info("RecipeServiceImpl.deleteRecipeById method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(recipe.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete recipe details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Recipe details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        recipeRepository.deleteById(recipeId);
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Recipe details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("RecipeServiceImpl.deleteRecipeById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteRecipes() {
        log.info("RecipeServiceImpl.deleteRecipes method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<Recipe> recipes = recipeRepository.findAll();
        if(recipes.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete all recipes details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Recipe all details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        recipeRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Recipe details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("RecipeServiceImpl.deleteRecipes method end");
        return commonResponse;
    }
}
