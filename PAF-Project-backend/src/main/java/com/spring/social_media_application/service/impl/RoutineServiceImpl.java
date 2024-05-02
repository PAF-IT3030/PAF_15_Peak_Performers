package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.RoutineDTO;
import com.spring.social_media_application.entity.Routine;
import com.spring.social_media_application.mapper.RoutineMapper;
import com.spring.social_media_application.repository.RoutineRepository;
import com.spring.social_media_application.service.RoutineService;
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
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;
    private final RoutineMapper routineMapper;

    @Override
    public CommonResponse getAllRoutinesDetails() {
        log.info("RoutineServiceImpl.getAllRoutinesDetails method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<RoutineDTO> routineDTOList = new ArrayList<>();
        List<Routine> routines = routineRepository.findAll();
        routines.forEach(routine ->  routineDTOList.add(routineMapper.domainToDto(routine)));
        if (routines.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<Routine>());
            commonResponse.setMessage("Routine details list not available!");
            log.warn("Routine details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Routine details are fetching success!");
        commonResponse.setData(routineDTOList);
        log.info("RoutineServiceImpl.getAllRoutinesDetails method end");
        return commonResponse;
    }

    @Override
    public CommonResponse getRoutineDetailsById(String routineId) {
        log.info("RoutineServiceImpl.getRoutineDetailsById method accessed");
        RoutineDTO routineDTO;
        CommonResponse commonResponse = new CommonResponse();
        Optional<Routine> routine = routineRepository.findById(routineId);
        if(routine.isPresent()) {
            routineDTO = routineMapper.domainToDto(routine.get());
        } else {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<>());
            commonResponse.setMessage("Routine details is not available!");
            log.warn("Routine details not available. message : {} ", commonResponse.getMessage());
            return commonResponse;
        }
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Routine details is fetching success!");
        commonResponse.setData(routineDTO);
        log.info("RoutineServiceImpl.getRoutineDetailsById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteRoutineById(String routineId) {
        log.info("RoutineServiceImpl.deleteRoutineById method accessed");
        CommonResponse commonResponse = new CommonResponse();
        Optional<Routine> routine = routineRepository.findById(routineId);
        if(routine.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete routine details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Routine details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        routineRepository.deleteById(routineId);
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Routine details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("RoutineServiceImpl.deleteRoutineById method end");
        return commonResponse;
    }

    @Override
    public CommonResponse deleteRoutines() {
        log.info("RoutineServiceImpl.deleteRoutines method accessed");
        CommonResponse commonResponse = new CommonResponse();
        List<Routine> routines = routineRepository.findAll();
        if(routines.isEmpty()) {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Delete all routines details not available!");
            commonResponse.setData(new ArrayList<>());
            log.warn("Routine all details not available. message : {}", commonResponse.getMessage());
            return commonResponse;
        }
        routineRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Exercises details is delete success!");
        commonResponse.setData(new ArrayList<>());
        log.info("RoutineServiceImpl.deleteRoutines method end");
        return commonResponse;
    }
}
