package com.spring.social_media_application.entity;

import com.spring.social_media_application.entity.authentication.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class WorkoutPlan {
    @Id
    private String id;
    private User user;
    private String name;
    private String description;
    private List<Routine> routines;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
