package com.spring.social_media_application.controller;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutStatusRequestDTO;
import com.spring.social_media_application.service.WorkoutStatusService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponhfhjfjhjyfhnseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout/status")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class WorkoutStatusController {
    private final WorkoutStatusService workoutStatusService;

    /**
     * Get all workout status
     *
     * @return success or fail response of workout status creation
     */
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllWorkoutStatusDetails() {
        CommonResponse commonResponse = workoutStatusService.getAllWorkoutStatusDetails();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Get workout status by planId
     *
     * @param workoutStatusId - required data for get workout status by planId
     * @return success or fail response of get workout status by planId
     */
    @GetMapping("/{workoutStatusId}")
    public ResponseEntity<CommonResponse> getWorkoutStatusDetailsById(@PathVariable("workoutStatusId") @NotNull String workoutStatusId) {
        CommonResponse commonResponse = workoutStatusService.getWorkoutStatusDetailsById(workoutStatusId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Create workout status
     *
     * @param workoutStatusRequestDTO - required data for workout status save
     * @return success or fail response of workout status save
     */
    @PostMapping("")
    public ResponseEntity<CommonResponse> saveWorkoutStatus(@Valid @RequestBody WorkoutStatusRequestDTO workoutStatusRequestDTO) {
        CommonResponse commonResponse = workoutStatusService.saveWorkoutStatus(workoutStatusRequestDTO);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Update workout status
     *
     * @param workoutStatusRequestDTO - required data for workout status update
     * @return success or fail response of workout status update
     */
    @PutMapping("")
    public ResponseEntity<CommonResponse> updateWorkoutStatus(@Valid @RequestBody WorkoutStatusRequestDTO workoutStatusRequestDTO) {
        CommonResponse commonResponse = workoutStatusService.updateWorkoutStatus(workoutStatusRequestDTO);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete workout status by planId
     *
     * @param workoutStatusId - required data for delete workout status by planId
     * @return success or fail response of delete workout status by planId
     */
    @DeleteMapping("/{workoutStatusId}")
    public ResponseEntity<CommonResponse> deleteWorkoutStatusDetailsById(@PathVariable("workoutStatusId") @NotNull String workoutStatusId) {
        CommonResponse commonResponse = workoutStatusService.deleteWorkoutStatusDetailsById(workoutStatusId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete all workout status
     *
     * @return success or fail response of delete all workout status
     */
    @DeleteMapping("")
    public ResponseEntity<CommonResponse> deleteALlWorkoutStatusDetails() {
        CommonResponse commonResponse = workoutStatusService.deleteALlWorkoutStatusDetails();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
