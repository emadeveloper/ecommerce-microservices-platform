package com.ecommerce.user_service.infrastructure.persistence.mapper;

import com.ecommerce.user_service.domain.model.User;
import com.ecommerce.user_service.domain.model.UserStatus;
import com.ecommerce.user_service.domain.valueobject.Email;
import com.ecommerce.user_service.infrastructure.persistence.entity.UserEntity;
import com.ecommerce.user_service.infrastructure.persistence.entity.UserStatusEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Domain User and JPA UserEntity.
 * This is a key component in Hexagonal Architecture:
 * - It translates between domain language and persistence language
 * - Keeps domain independent of JPA
 */

@Component
public class UserEntityMapper {

    /**
     * Converts domain User to JPA UserEntity.
     *
     * @param user domain user
     * @return JPA entity
     */
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail().getAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .status(toEntityStatus(user.getStatus()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    /**
     * Converts JPA UserEntity to domain User.
     *
     * @param entity JPA entity
     * @return domain user
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                entity.getId(),
                new Email(entity.getEmail()),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                toDomainStatus(entity.getStatus()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    /**
     * Converts domain UserStatus to entity UserStatusEntity.
     */
    private UserStatusEntity toEntityStatus(UserStatus status) {
        if (status == null) {
            return null;
        }

        return switch (status) {
            case ACTIVE -> UserStatusEntity.ACTIVE;
            case INACTIVE -> UserStatusEntity.INACTIVE;
            case DELETED -> UserStatusEntity.DELETED;
        };
    }

    /**
     * Converts entity UserStatusEntity to domain UserStatus.
     */
    private UserStatus toDomainStatus(UserStatusEntity status) {
        if (status == null) {
            return null;
        }

        return switch (status) {
            case ACTIVE -> UserStatus.ACTIVE;
            case INACTIVE -> UserStatus.INACTIVE;
            case DELETED -> UserStatus.DELETED;
        };
    }
}
