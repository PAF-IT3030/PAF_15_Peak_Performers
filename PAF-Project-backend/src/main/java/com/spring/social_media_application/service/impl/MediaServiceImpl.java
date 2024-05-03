package com.spring.social_media_application.service.impl;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.MediaDTO;
import com.spring.social_media_application.entity.MediaEntity;
import com.spring.social_media_application.entity.WorkoutStatus;
import com.spring.social_media_application.exception.ReferenceNotFoundException;
import com.spring.social_media_application.mapper.MediaMapper;
import com.spring.social_media_application.repository.MediaRepository;
import com.spring.social_media_application.service.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Override
    public CommonResponse uploadImage(MediaDTO mediaDTO) throws IOException {
        log.info("MediaServiceImpl.uploadImage method end");
        CommonResponse commonResponse = new CommonResponse();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(mediaDTO.getFile().getOriginalFilename()));

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setData(mediaDTO.getFile().getBytes());
        mediaEntity.setFileName(fileName);
        mediaEntity.setContentType(mediaDTO.getFile().getContentType());
        mediaEntity.setDescription(mediaDTO.getDescription());
        MediaEntity savedMedia = mediaRepository.save(mediaEntity);

        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Image uploaded successfully. File name: " + savedMedia.getFileName());
        commonResponse.setData(mediaMapper.domainToDto(savedMedia));
        log.info("MediaServiceImpl.uploadImage method end");
        return commonResponse;
    }

    @Override
    public CommonResponse uploadVideo(MediaDTO mediaDTO) throws IOException {
        CommonResponse commonResponse = new CommonResponse();
        if (mediaDTO.getFile().getSize() > 32 * 1024 * 1024) {
            throw new IllegalArgumentException("Video file size exceeds the limit of 32MB.");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(mediaDTO.getFile().getOriginalFilename()));
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setData(mediaDTO.getFile().getBytes());
        mediaEntity.setFileName(fileName);
        mediaEntity.setContentType(mediaDTO.getFile().getContentType());
        mediaEntity.setDescription(mediaDTO.getDescription());
        MediaEntity savedMedia = mediaRepository.save(mediaEntity);

        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Video uploaded successfully. File name: " + savedMedia.getFileName());
        commonResponse.setData(mediaMapper.domainToDto(savedMedia));
        return commonResponse;
    }

    @Override
    public Optional<byte[]> getImageData(String id) {
        Optional<MediaEntity> mediaEntityOptional = mediaRepository.findById(id);
        if (mediaEntityOptional.isEmpty()) {
            throw new ReferenceNotFoundException("The Media is not exists");
        }
        return mediaEntityOptional.map(MediaEntity::getData);
    }

    @Override
    public List<MediaEntity> getImageLisData() {
        List<MediaEntity> mediaEntityOptional = mediaRepository.findAll();
        if (mediaEntityOptional.isEmpty()) {
            throw new ReferenceNotFoundException("The Media not exists");
        }
        return mediaEntityOptional;
    }

    @Override
    public Optional<byte[]> getVideoData(String id) {
        Optional<MediaEntity> mediaEntityOptional = mediaRepository.findById(id);
        if (mediaEntityOptional.isEmpty()) {
            throw new ReferenceNotFoundException("The Media not exists");
        }
        return mediaEntityOptional.map(MediaEntity::getData);
    }

    @Override
    public CommonResponse deleteMediaById(String id) {
        CommonResponse commonResponse = new CommonResponse();
        Optional<MediaEntity> savedMedia = mediaRepository.findById(id);
        if (savedMedia.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<WorkoutStatus>());
            commonResponse.setMessage("Media details list not available!");
            log.warn("Media details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        mediaRepository.deleteById(id);
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("Media details are deleting success!");
        commonResponse.setData(new ArrayList<>());
        return commonResponse;
    }

    @Override
    public CommonResponse deleteAllMedia() {
        CommonResponse commonResponse = new CommonResponse();
        List<MediaEntity> mediaEntityList = mediaRepository.findAll();
        if (mediaEntityList.isEmpty()) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(new ArrayList<WorkoutStatus>());
            commonResponse.setMessage("Media list details list not available!");
            log.warn("Media list details not available. message :{}", commonResponse.getMessage());
            return commonResponse;
        }
        mediaRepository.deleteAll();
        commonResponse.setStatus(HttpStatus.OK);
        commonResponse.setMessage("All Media details are deleting success!");
        commonResponse.setData(new ArrayList<>());
        return commonResponse;
    }

}
