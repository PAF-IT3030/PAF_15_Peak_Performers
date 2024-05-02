package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.WorkoutStatusRequestDTO;
import com.spring.social_media_application.entity.WorkoutStatus;
import com.spring.social_media_application.mapper.WorkoutStatusMapper;
import com.spring.social_media_application.repository.WorkoutStatusRepository;
import com.spring.social_media_application.service.WorkoutStatusService;
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
public class WorkoutStatusServiceImpl implements WorkoutStatusService {
    private final WorkoutStatusRepository workoutStatusRepository;
    private final WorkoutStatusMapper workoutStatusMapper;
    @Override
    public CommonResponse getAllWorkoutStatusDetails() {
        log.info("WorkoutStatusServiceImpl.getAllWorkoutStatusDetails method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<WorkoutStatusRequestDTO> workoutStatusRequestDTOList = new ArrayList<>();
        List<WorkoutStatus> workoutStatusList = workoutStatusRepository.findAll();
        workoutStatusList.forEach(notification ->  workoutStatusRequestDTOList.add(workoutStatusMapper.domainToDto(notification)));
        if (workoutStatusList.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<WorkoutStatus>());
            commonResponse.setMessage("WorkoutStatus details list not available!");
            log.warn("Workout status details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutStatus details are fetching success!");
        commonResponse.setData(workoutStatusRequestDTOList);
        log.info("WorkoutStatusServiceImpl.getAllWorkoutStatusDetails method end");
        return commonResponse;
    }

    @Override
    public CommonResponse getWorkoutStatusDetailsById(String workoutStatusId) {
        log.info("WorkoutStatusServiceImpl.getWorkoutStatusDetailsById method accessed");
        WorkoutStatusRequestDTO workoutStatusRequestDTO;
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutStatus> workoutStatus = workoutStatusRepository.findById(workoutStatusId);
        if(workoutStatus.isPresent()) {
            workoutStatusRequestDTO = workoutStatusMapper.domainToDto(workoutStatus.get());
        } else {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<>());
            commonResponse.setMessage("WorkoutStatus details is not available!");
            log.warn("WorkoutStatus details not available. message : {} ", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutStatus details is fetching success!");
        commonResponse.setData(workoutStatusRequestDTO);
        log.info("WorkoutStatusServiceImpl.getWorkoutStatusDetailsById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse saveWorkoutStatus(WorkoutStatusRequestDTO workoutStatusRequestDTO) {
        log.info("WorkoutStatusServiceImpl.saveWorkoutStatus method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutStatus> workoutStatus = workoutStatusRepository.findById(workoutStatusRequestDTO.getId());
        if(workoutStatus.isPresent()){
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("WorkoutStatus details already exist!");
            commonResponse.setData(workoutStatusMapper.domainToDto(workoutStatus.get()));
            log.warn("Workout Status details not exist. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        WorkoutStatus workoutStatusSavedDetails = workoutStatusRepository.save(workoutStatusMapper.dtoToDomain(workoutStatusRequestDTO, new WorkoutStatus()));
        commonResponse.setStatus(HttpStatus.CREATED);
        commonResponse.setMessage("WorkoutStatus details saved success!");
        commonResponse.setData(workoutStatusMapper.domainToDto(workoutStatusSavedDetails));
        log.info("WorkoutStatusServiceImpl.saveWorkoutStatus method end");
        return commonResponse;
    }

    @Override
    public CommonResponse updateWorkoutStatus(WorkoutStatusRequestDTO workoutStatusRequestDTO) {
        log.info("WorkoutStatusServiceImpl.updateWorkoutStatus method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutStatus> workoutStatus = workoutStatusRepository.findById(workoutStatusRequestDTO.getId());
        if(workoutStatus.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("WorkoutStatus details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Workout status  details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        WorkoutStatus workoutStatusUpdatedDetails = workoutStatusRepository.save(workoutStatusMapper.dtoToDomain(workoutStatusRequestDTO, workoutStatus.get()));
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutStatus details is update success!");
        commonResponse.setData(workoutStatusMapper.domainToDto(workoutStatusUpdatedDetails));
        log.info("WorkoutStatusServiceImpl.updateWorkoutStatus method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteWorkoutStatusDetailsById(String workoutStatusId) {
        log.info("WorkoutStatusServiceImpl.deleteWorkoutStatusDetailsById method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<WorkoutStatus> workoutStatus = workoutStatusRepository.findById(workoutStatusId);
        if(workoutStatus.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete workoutStatus details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("WorkoutStatus  details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        workoutStatusRepository.deleteById(workoutStatusId);
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutStatus details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("WorkoutStatusServiceImpl.deleteWorkoutStatusDetailsById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteALlWorkoutStatusDetails() {
        log.info("WorkoutStatusServiceImpl.deleteALlWorkoutStatusDetails method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<WorkoutStatus> workoutStatus = workoutStatusRepository.findAll();
        if(workoutStatus.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete all workoutStatus details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("WorkoutStatus all details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        workoutStatusRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("WorkoutStatus all details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("WorkoutStatusServiceImpl.deleteALlWorkoutStatusDetails method end");
        return commonResponse;
    }
}
