package com.spring.social_media_application.service;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutPlanDTO;

public interface WorkoutPlanService {
    /**
     * Get all workout plans
     *
     * @return success or fail response of workout plans fetching
     */
    CommonResponse getAllWorkoutPlansDetails();

    /**
     * Get workout plan
     *
     * @param workoutPlanId - required data for get workout plan
     * @return success or fail response of get workout plan
     */
    CommonResponse getWorkoutPlanDetailsById(String workoutPlanId);
    /**
     * save workout plan
     *
     * @param workoutPlanDTO - required data for workout plan save
     * @return success or fail response of workout plan save
     */
    CommonResponse saveWorkoutPlan(WorkoutPlanDTO workoutPlanDTO);

    /**
     * Delete workout plans
     *
     * @return success or fail response of delete workout plans
     */
    CommonResponse deleteWorkoutPlans();

    /**
     * Delete workout plan
     *
     * @param workoutPlanId - required data for delete workout plan
     * @return success or fail response of delete workout plan
     */
    CommonResponse deleteWorkoutPlanById(String workoutPlanId);

    /**
     * Update workout plan
     *
     * @param workoutPlanDTO - required data for workout plan update
     * @return success or fail response of workout plan update
     */
    CommonResponse updateWorkPlan(WorkoutPlanDTO workoutPlanDTO);
}
