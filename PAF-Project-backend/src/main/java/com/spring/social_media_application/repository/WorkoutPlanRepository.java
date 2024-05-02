package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.WorkoutPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutPlanRepository extends MongoRepository<WorkoutPlan, String> {
}
