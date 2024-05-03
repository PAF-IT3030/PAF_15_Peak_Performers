package com.spring.social_media_application.controller;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.MealPlanDTO;
import com.spring.social_media_application.service.MealPlanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal/plan")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MealPlanController {
    private final MealPlanService mealPlanService;

    /**
     * Get all meal plans
     *
     * @return success or fail response of eal plans fetching
     */
    @GetMapping("")
    public ResponseEntity<CommonResponse> getAllMealPlanDetails() {
        CommonResponse commonResponse = mealPlanService.getAllMealPlanDetails();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Get meal plan
     *
     * @param mealPlanId - required data for get meal plan
     * @return success or fail response of get meal plan
     */
    @GetMapping("/{mealPlanId}")
    public ResponseEntity<CommonResponse> getMealPlanDetailsById(
            @PathVariable("mealPlanId") @NotNull String mealPlanId) {
        CommonResponse commonResponse = mealPlanService.getMealPlanDetailsById(mealPlanId);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * save meal plan
     *
     * @param mealPlanDTO - required data for meal plan save
     * @return success or fail response of meal plan save
     */
    @PostMapping("")
    public ResponseEntity<CommonResponse> saveMealPlan(@Valid @RequestBody MealPlanDTO mealPlanDTO) {
        CommonResponse commonResponse = mealPlanService.saveMealPlan(mealPlanDTO);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

}
