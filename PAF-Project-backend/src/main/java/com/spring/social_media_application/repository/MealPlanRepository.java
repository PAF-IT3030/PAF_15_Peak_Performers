package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
}
