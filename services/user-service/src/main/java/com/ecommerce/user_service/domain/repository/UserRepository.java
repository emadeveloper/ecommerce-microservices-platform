package com.ecommerce.user_service.domain.repository;

import com.ecommerce.user_service.domain.model.User;
import com.ecommerce.user_service.domain.valueobject.Email;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for User aggregate.
 * This is a PORT in Hexagonal Architecture.
 * The infrastructure layer will provide the ADAPTER (implementation).
 * Note: This interface uses domain objects (User, Email),
 * not persistence objects (UserEntity).
 */

public interface UserRepository {
    /**
     * Saves a user (create or update).
     *
     * @param user the user to save
     * @return the saved user
     */
    User save (User user);

    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return an Optional containing the user if found
     */
    Optional<User> findById(UUID id);

    /**
     * Finds a user by email.
     *
     * @param email the user email
     * @return an Optional containing the user if found
     */
    Optional<User> findByEmail(Email email);

    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     */
    void deleteUser(UUID id);

    /**
     * Checks if a user exists with the given email.
     *
     * @param email the email to check
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(Email email);
}
