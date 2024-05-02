package com.spring.social_media_application.repository.authentication;

import com.spring.social_media_application.entity.authentication.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findUserByUserIdIgnoreCase(String userId);
}
