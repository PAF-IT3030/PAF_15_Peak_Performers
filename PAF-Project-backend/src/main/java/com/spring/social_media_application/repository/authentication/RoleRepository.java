package com.spring.social_media_application.repository.authentication;


import com.spring.social_media_application.entity.authentication.ERole;
import com.spring.social_media_application.entity.authentication.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
