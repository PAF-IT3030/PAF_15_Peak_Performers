package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.Routine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoutineRepository extends MongoRepository<Routine, String> {
}
