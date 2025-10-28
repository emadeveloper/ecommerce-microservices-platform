package com.ecommerce.user_service.domain.model;

/**
 * Represents the possible states of a user in the system.
 * Business rules:
 * - ACTIVE: User can perform all operations
 * - INACTIVE: User is temporarily disabled (can be reactivated)
 * - DELETED: User is soft-deleted (cannot be reactivated)
 */

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    DELETED
}
