package com.ecommerce.user_service.infrastructure.persistence.entity;

/**
 * Enum for user status at the persistence layer.
 * Mirrors the domain UserStatus but lives in infrastructure.
 */

public enum UserStatusEntity {
    ACTIVE,
    INACTIVE,
    DELETED
}
