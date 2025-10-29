package com.ecommerce.user_service.infrastructure.persistence.repository;

import com.ecommerce.user_service.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for UserEntity.
 * This is the "magic" Spring interface that provides CRUD operations.
 * Spring will generate the implementation automatically at runtime.
 */

@Repository
public interface JpaUserRepositorySpring extends JpaRepository<UserEntity, UUID> {

    /**
     * Find user by email.
     * Spring Data JPA will generate the query automatically from the method name.
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Check if user exists by email.
     */
    boolean existsByEmail(String email);
}
