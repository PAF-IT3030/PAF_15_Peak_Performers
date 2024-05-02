package com.spring.social_media_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition {
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
}
