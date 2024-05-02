package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.MediaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<MediaEntity, String> {
}
