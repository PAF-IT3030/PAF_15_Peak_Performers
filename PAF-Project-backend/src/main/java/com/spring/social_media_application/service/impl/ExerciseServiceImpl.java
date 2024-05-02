package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.entity.Exercise;
import com.spring.social_media_application.repository.ExerciseRepository;
import com.spring.social_media_application.service.ExerciseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    @Override
    public CommonResponse deleteExercises() {
        log.info("ExerciseServiceImpl.deleteExercises method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<Exercise> exercises = exerciseRepository.findAll();
        if(exercises.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete all exercises details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Exercise all details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        exerciseRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Exercises details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("ExerciseServiceImpl.deleteExercises method end");
        return commonResponse;
    }
}
