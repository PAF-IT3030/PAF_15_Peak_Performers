package com.spring.social_media_application.mapper;

import com.spring.social_media_application.dto.MediaResponseDTO;
import com.spring.social_media_application.entity.MediaEntity;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {
    public MediaResponseDTO domainToDto(MediaEntity domain) {
        if (domain == null) {
            throw new ReferenceNotFoundException("The WorkoutStatus should not be null");
        }
        MediaResponseDTO dto = new MediaResponseDTO();
        dto.setId(domain.getId());
        dto.setFileName(domain.getFileName());
        dto.setContentType(domain.getContentType());
        dto.setDescription(domain.getDescription());
        return dto;
    }
}
