package com.spring.social_media_application.service;

import com.spring.social_media_application.common.CommonResponse;

public interface RoutineService {

    /**
     * Get all routines
     *
     * @return success or fail response of routines fetching
     */
    CommonResponse getAllRoutinesDetails();

    /**
     * Get routine
     *
     * @param routineId - required data for get routine
     * @return success or fail response of get routine
     */
    CommonResponse getRoutineDetailsById(String routineId);

    /**
     * Delete routine
     *
     * @param routineId - required data for delete routine
     * @return success or fail response of delete routine
     */
    CommonResponse deleteRoutineById(String routineId);

    /**
     * Delete exercises
     *
     * @return success or fail response of delete exercises
     */
    CommonResponse deleteRoutines();

}
