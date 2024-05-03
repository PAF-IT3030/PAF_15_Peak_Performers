package com.spring.social_media_application.service;

import com.spring.social_media_application.common.CommonResponse;

public interface RecipeService {
    /**
     * Get all recipes
     *
     * @return success or fail response of recipes fetching
     */
    CommonResponse getAllRecipeDetails();

    /**
     * Get recipe
     *
     * @param recipeId - required data for get recipe
     * @return success or fail response of get recipe
     */
    CommonResponse getRecipeDetailsById(String recipeId);

    /**
     * Delete recipe
     *
     * @param recipeId - required data for delete recipe
     * @return success or fail response of delete recipe
     */
    CommonResponse deleteRecipeById(String recipeId);

    /**
     * Delete recipes
     *
     * @return success or fail response of delete recipes
     */
    CommonResponse deleteRecipes();

}
