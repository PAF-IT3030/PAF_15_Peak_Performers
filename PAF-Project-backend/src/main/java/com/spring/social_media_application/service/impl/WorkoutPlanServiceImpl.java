package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutPlanDTO;
import com.spring.social_media_application.entity.WorkoutPlan;
import com.spring.social_media_application.mapper.WorkoutPlanMapper;
import com.spring.social_media_application.repository.WorkoutPlanRepository;
import com.spring.social_media_application.service.WorkoutPlanService;
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
public class WorkoutPlanServiceImpl implements WorkoutPlanService {
    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutPlanMapper workoutPlanMapper;

    @Override
    public CommonResponse getAllWorkoutPlansDetails() {
        log.info("WorkoutPlanServiceImpl.getAllWorkoutPlansDetails method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<WorkoutPlanDTO> workoutPlanDTOList = new ArrayList<>();
        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findAll();
        workoutPlans.forEach(workoutPlan ->  workoutPlanDTOList.add(workoutPlanMapper.domainToDto(workoutPlan)));
        if (workoutPlans.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<WorkoutPlan>());
            commonResponse.setMessage("WorkoutPlan details list not available!");
            log.warn("WorkoutPlan details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutPlan details are fetching success!");
        commonResponse.setData(workoutPlanDTOList);
        log.info("WorkoutPlanServiceImpl.getAllWorkoutPlansDetails method end");
        return commonResponse;
    }

    @Override
    public CommonResponse getWorkoutPlanDetailsById(String workoutPlanId) {
        log.info("WorkoutPlanServiceImpl.getWorkoutPlanDetailsById method accessed");
        WorkoutPlanDTO workoutPlanDTO;
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanId);
        if(workoutPlan.isPresent()) {
            workoutPlanDTO = workoutPlanMapper.domainToDto(workoutPlan.get());
        } else {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<>());
            commonResponse.setMessage("WorkoutPlan details is not available!");
            log.warn("WorkoutPlan details not available. message : {} ", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutPlan details is fetching success!");
        commonResponse.setData(workoutPlanDTO);
        log.info("WorkoutPlanServiceImpl.getWorkoutPlanDetailsById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse saveWorkoutPlan(WorkoutPlanDTO workoutPlanDTO) {
        log.info("WorkoutPlanServiceImpl.saveWorkoutPlan method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanDTO.getPlanId());
        if(workoutPlan.isPresent()){
            WorkoutPlanDTO foundWorkoutPlanDTO = workoutPlanMapper.domainToDto(workoutPlan.get());
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("WorkoutPlan details already exist!");
            commonResponse.setData(foundWorkoutPlanDTO);
            log.warn("WorkoutPlan details already exist. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        WorkoutPlan workoutPlanSavedDetails = workoutPlanRepository.save(workoutPlanMapper.dtoToDomain(workoutPlanDTO, new WorkoutPlan()));
        commonResponse.setStatus(HttpStatus.CREATED);
        commonResponse.setMessage("WorkoutPlan details saved success!");
        commonResponse.setData(workoutPlanMapper.domainToDto(workoutPlanSavedDetails));
        log.info("WorkoutPlanServiceImpl.saveWorkoutPlan method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteWorkoutPlanById(String workoutPlanId) {
        log.info("WorkoutPlanServiceImpl.deleteWorkoutPlanById method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanId);
        if(workoutPlan.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete workoutPlan details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("WorkoutPlan  details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        workoutPlanRepository.deleteById(workoutPlanId);
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutPlan details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("WorkoutPlanServiceImpl.deleteWorkoutPlanById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse updateWorkPlan(WorkoutPlanDTO workoutPlanDTO) {
        log.info("MealPlanServiceImpl.updateMealPlan method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(workoutPlanDTO.getPlanId());
        if(workoutPlan.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Update workoutPlan details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("WorkoutPlan details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        WorkoutPlan workoutPlanUpdatedDetails = workoutPlanRepository.save(workoutPlanMapper.dtoToDomain(workoutPlanDTO, workoutPlan.get()));
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("TimeTable details is update success!");
        commonResponse.setData(workoutPlanMapper.domainToDto(workoutPlanUpdatedDetails));
        log.info("MealPlanServiceImpl.updateMealPlan method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteWorkoutPlans() {
        log.info("WorkoutPlanServiceImpl.deleteWorkoutPlans method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<WorkoutPlan> workoutPlans = workoutPlanRepository.findAll();
        if(workoutPlans.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete all workoutPlans details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("WorkoutPlan all details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        workoutPlanRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutPlan all details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("WorkoutPlanServiceImpl.deleteWorkoutPlans method end");
        return commonResponse;
    }

}
