package com.ecommerce.user_service.infrastructure.persistence.repository;

import com.ecommerce.user_service.domain.model.User;
import com.ecommerce.user_service.domain.repository.UserRepository;
import com.ecommerce.user_service.domain.valueobject.Email;
import com.ecommerce.user_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.user_service.infrastructure.persistence.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * JPA implementation of UserRepository (domain interface).
 * This is an ADAPTER in Hexagonal Architecture:
 * - Implements the PORT (UserRepository interface from domain)
 * - Uses JPA infrastructure to persist data
 * - Translates between domain objects and JPA entities
 * The @Repository annotation makes this a Spring bean and enables exception translation.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepository {

    private final JpaUserRepositorySpring jpaRepository;
    private final UserEntityMapper mapper;


    @Override
    public User save(User user) {
        log.debug("Saving user with id: {}", user.getId());

        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        User savedUser = mapper.toDomain(savedEntity);

        log.debug("User saved successfully with id: {} ", savedUser.getId());
        return savedUser;
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.debug("Finding user with id: {} ", id);

        return jpaRepository.findById(id)
                .map(entity -> {
                    User user = mapper.toDomain(entity);
                    log.debug("User found with id: {} ", id);
                    return user;
                });
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        log.debug("Finding user by email: {}", email.getAddress());

        return jpaRepository.findByEmail(email.getAddress())
                .map(entity -> {
                    User user = mapper.toDomain(entity);
                    log.debug("User found with email: {}", email.getAddress());
                    return user;
                });
    }

    @Override
    public void deleteUser(UUID id) {

        log.debug("Deleting user with id: {} ", id);
        jpaRepository.deleteById(id);
        log.debug("User deleted with id: {} ", id);
    }

    @Override
    public boolean existsByEmail(Email email) {
        log.debug("Checking if user exists with email: {} ", email.getAddress());
        boolean exists = jpaRepository.existsByEmail(email.getAddress());
        log.debug("User found with email {}: {} ", email.getAddress(), exists);
        return exists;
    }
}