package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.WorkoutStatusRequestDTO;
import com.spring.social_media_application.dto.WorkoutStatusResponseDTO;
import com.spring.social_media_application.entity.WorkoutStatus;
import com.spring.social_media_application.entity.authentication.User;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import com.spring.social_media_application.repository.authentication.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkoutStatusMapper {
    private final UserRepository userRepository;
    public WorkoutStatus dtoToDomain(WorkoutStatusRequestDTO dto, WorkoutStatus domain) {
        if (dto == null) {
            throw new ReferenceNotFoundException("The WorkoutStatusRequestDTO should not be null");
        }
        User user = userRepository.findUserByUserIdIgnoreCase(dto.getUserId()).orElse(new User());
        domain.setUser(user);
        domain.setDistance(dto.getDistance());
        domain.setPushUp(dto.getPushUp());
        domain.setWeightLifted(dto.getWeightLifted());
        domain.setDescription(dto.getDescription());
        return domain;
    }

    public WorkoutStatusRequestDTO domainToDto(WorkoutStatus domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The WorkoutStatus should not be null");
        }
        WorkoutStatusRequestDTO dto = new WorkoutStatusRequestDTO();
        dto.setId(domain.getId());
        dto.setDistance(domain.getDistance());
        dto.setPushUp(domain.getPushUp());
        dto.setWeightLifted(domain.getWeightLifted());
        dto.setDescription(domain.getDescription());
        return dto;
    }

    public WorkoutStatusResponseDTO domainToResponseDto(WorkoutStatus domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The enrolment should not be null");
        }
        WorkoutStatusResponseDTO dto = new WorkoutStatusResponseDTO();
        dto.setId(domain.getId());
        dto.setUser(domain.getUser());
        dto.setDistance(domain.getDistance());
        dto.setPushUp(domain.getPushUp());
        dto.setWeightLifted(domain.getWeightLifted());
        dto.setDescription(domain.getDescription());
        return dto;
    }
}
