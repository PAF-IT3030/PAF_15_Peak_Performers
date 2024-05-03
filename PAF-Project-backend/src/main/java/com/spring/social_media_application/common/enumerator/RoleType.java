package com.spring.social_media_application.common.enumerator;

public enum RoleType {
    ROLE_STUDENT,
    ROLE_FACULTY,
    ROLE_ADMIN;

    public String getRoleType() {
        return switch (this) {
            case ROLE_STUDENT -> "student";
            case ROLE_FACULTY -> "faculty";
            case ROLE_ADMIN -> "admin";
        };
    }
}
