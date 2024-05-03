package com.spring.social_media_application.service;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutStatusRequestDTO;

public interface WorkoutStatusService {
    /**
     * Get all workout status
     *
     * @return success or fail response of workout status creation
     */
    CommonResponse getAllWorkoutStatusDetails();

    /**
     * Get workout status by planId
     *
     * @param workoutStatusId - required data for get workout status by planId
     * @return success or fail response of get workout status by planId
     */
    CommonResponse getWorkoutStatusDetailsById(String workoutStatusId);

    /**
     * Create workout status
     *
     * @param workoutStatusRequestDTO - required data for workout status save
     * @return success or fail response of workout status save
     */
    CommonResponse saveWorkoutStatus(WorkoutStatusRequestDTO workoutStatusRequestDTO);

    /**
     * Update workout status
     *
     * @param workoutStatusRequestDTO - required data for workout status update
     * @return success or fail response of workout status update
     */
    CommonResponse updateWorkoutStatus(WorkoutStatusRequestDTO workoutStatusRequestDTO);

    /**
     * Delete workout status by planId
     *
     * @param workoutStatusId - required data for delete workout status by planId
     * @return success or fail response of delete workout status by planId
     */
    CommonResponse deleteWorkoutStatusDetailsById(String workoutStatusId);

    /**
     * Delete all workout status
     *
     * @return success or fail response of delete all workout status
     */
    CommonResponse deleteALlWorkoutStatusDetails();
}
