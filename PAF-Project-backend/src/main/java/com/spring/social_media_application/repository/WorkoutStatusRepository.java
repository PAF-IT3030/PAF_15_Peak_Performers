package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.WorkoutStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutStatusRepository extends MongoRepository<WorkoutStatus, String> {
}
