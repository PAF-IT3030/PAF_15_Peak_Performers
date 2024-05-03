package com.spring.social_media_application.service;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.MealPlanDTO;

public interface MealPlanService {

    /**
     * Get all meal plans
     *
     * @return success or fail response of eal plans fetching
     */
    CommonResponse getAllMealPlanDetails();

    /**
     * Get meal plan
     *
     * @param mealPlanId - required data for get meal plan
     * @return success or fail response of get meal plan
     */
    CommonResponse getMealPlanDetailsById(String mealPlanId);

    /**
     * Delete meal plan
     *
     * @return success or fail response of delete meal plan
     */
    CommonResponse deleteMealPlans();

    /**
     * Delete meal plan
     *
     * @param mealPlanId - required data for delete meal plan
     * @return success or fail response of delete meal plan
     */
    CommonResponse deleteMealPlanById(String mealPlanId);

    /**
     * save meal plan
     *
     * @param mealPlanDTO - required data for meal plan save
     * @return success or fail response of meal plan save
     */
    CommonResponse saveMealPlan(MealPlanDTO mealPlanDTO);

    /**
     * Update meal plan
     *
     * @param mealPlanDTO - required data for meal plan update
     * @return success or fail response of meal plan update
     */
    CommonResponse updateMealPlan(MealPlanDTO mealPlanDTO);
}
