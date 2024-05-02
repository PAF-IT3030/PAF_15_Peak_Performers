package com.spring.social_media_application.controller;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutPlanDTO;
import com.spring.social_media_application.service.RoutineService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout/routine")
@Slf4j
@AllArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    /**
     * Get all routines
     *
     * @return success or fail response of routines fetching
     */
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllRoutinesDetails() {
        CommonResponse commonResponse = routineService.getAllRoutinesDetails();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Get routine
     *
     * @param routineId - required data for get routine
     * @return success or fail response of get routine
     */
    @GetMapping("/{routineId}")
    public ResponseEntity<CommonResponse> getRoutineDetailsById(@PathVariable("routineId") @NotNull String routineId) {
        CommonResponse commonResponse = routineService.getRoutineDetailsById(routineId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete routine
     *
     * @param routineId - required data for delete routine
     * @return success or fail response of delete routine
     */
    @DeleteMapping("/{routineId}")
    public ResponseEntity<CommonResponse> deleteRoutineById(@PathVariable("routineId") @NotNull String routineId) {
        CommonResponse commonResponse = routineService.deleteRoutineById(routineId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete exercises
     *
     * @return success or fail response of delete exercises
     */
    @DeleteMapping("")
    public ResponseEntity<CommonResponse> deleteRoutines() {
        CommonResponse commonResponse = routineService.deleteRoutines();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
