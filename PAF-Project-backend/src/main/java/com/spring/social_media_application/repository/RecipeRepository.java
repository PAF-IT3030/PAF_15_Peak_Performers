package com.spring.social_media_application.repository;

import com.spring.social_media_application.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
}
