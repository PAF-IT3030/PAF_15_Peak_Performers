package com.spring.social_media_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "media")
public class MediaEntity {
    @Id
    private String id;
    private String fileName;
    private String contentType;
    private String description;
    private byte[] data;

}
