package com.spring.social_media_application.controller;

import com.spring.social_media_application.common.CommonResponse;
import com.spring.social_media_application.dto.MediaDTO;
import com.spring.social_media_application.entity.MediaEntity;
import com.spring.social_media_application.service.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/media")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MediaController {

    private final MediaService mediaService;

    /**
     * Upload Image
     *
     * @param file - required data for upload image
     * @return success or fail response of upload image
     */
    @PostMapping("/upload/image")
    public ResponseEntity<CommonResponse> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) {
        try {
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setFile(file);
            mediaDTO.setDescription(description);
            CommonResponse commonResponse = mediaService.uploadImage(mediaDTO);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (IOException e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setMessage("Failed to upload image:" + e.getMessage());
            commonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
    }

    /**
     * Upload Video
     *
     * @param file - required data for upload video
     * @return success or fail response of upload video
     */
    @PostMapping("/upload/video")
    public ResponseEntity<CommonResponse> uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) {
        try {
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setFile(file);
            mediaDTO.setDescription(description);
            CommonResponse commonResponse = mediaService.uploadVideo(mediaDTO);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (IOException e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setMessage("Failed to upload video: " + e.getMessage());
            commonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }
    }

    /**
     * Get Image
     *
     * @param id - required data for get image
     * @return success or fail response of get image
     */
    @GetMapping(value = "/download/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> downloadImage(@PathVariable String id) {
        Optional<byte[]> imageData = mediaService.getImageData(id);
        return imageData.map(bytes -> ResponseEntity.ok().body(bytes)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get Images
     *
     * @return success or fail response of get images
     */
    @GetMapping(value = "/download/images")
    public ResponseEntity<List<MediaEntity>> downloadImages() {
        List<MediaEntity> imageData = mediaService.getImageLisData();
        return new ResponseEntity<>(imageData, HttpStatus.OK);
    }

    /**
     * Get Video
     *
     * @param id - required data for get video
     * @return success or fail response of get video
     */
    @GetMapping(value = "/download/video/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadVideo(@PathVariable String id) {
        Optional<byte[]> videoData = mediaService.getVideoData(id);
        return videoData.map(bytes -> ResponseEntity.ok().body(bytes)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete image
     *
     * @param id - required data for delete image
     * @return success or fail response of delete image
     */
    @DeleteMapping("/delete/media/{id}")
    public ResponseEntity<CommonResponse> deleteImage(@PathVariable String id) {
        CommonResponse commonResponse = mediaService.deleteMediaById(id);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /**
     * Delete images
     *
     * @return success or fail response of delete images
     */
    @DeleteMapping("/delete/allMedia")
    public ResponseEntity<CommonResponse> deleteImage() {
        CommonResponse commonResponse = mediaService.deleteAllMedia();
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
