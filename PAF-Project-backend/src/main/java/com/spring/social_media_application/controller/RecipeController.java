package com.spring.social_media_application.controller;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.service.RecipeService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal/recipe")
@Slf4j
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    /**
     * Get all recipes
     *
     * @return success or fail response of recipes fetching
     */
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllRecipeDetails() {
        CommonResponse commonResponse = recipeService.getAllRecipeDetails();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Get recipe
     *
     * @param recipeId - required data for get recipe
     * @return success or fail response of get recipe
     */
    @GetMapping("/{recipeId}")
    public ResponseEntity<CommonResponse> getRecipeDetailsById(@PathVariable("recipeId") @NotNull String recipeId) {
        CommonResponse commonResponse = recipeService.getRecipeDetailsById(recipeId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete recipe
     *
     * @param recipeId - required data for delete recipe
     * @return success or fail response of delete recipe
     */
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<CommonResponse> deleteRecipeById(@PathVariable("recipeId") @NotNull String recipeId) {
        CommonResponse commonResponse = recipeService.deleteRecipeById(recipeId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete recipes
     *
     * @return success or fail response of delete recipes
     */
    @DeleteMapping("")
    public ResponseEntity<CommonResponse> deleteRecipes() {
        CommonResponse commonResponse = recipeService.deleteRecipes();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
