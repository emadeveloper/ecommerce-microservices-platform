package com.ecommerce.user_service.domain.model;

import com.ecommerce.user_service.domain.valueobject.Email;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * User domain entity.
 * This represents a user in the business domain with all its rules and behaviors.
 * It's framework-agnostic (no Spring, no JPA annotations).
 * Business invariants:
 * - A user must always have a valid email
 * - A user must have first and last name
 * - A deleted user cannot be activated
 * - Timestamps must be consistent (createdAt <= updatedAt)
 */
@Data
public class User {

    private final UUID id;
    private final Email email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Constructor for creating a new user.
     * Used when creating a user for the first time.
     */

    public User (UUID id, Email email, String firstName, String lastName,
                 String phoneNumber, UserStatus status,
                LocalDateTime createdAt, LocalDateTime updatedAt){
        // Validations
        Objects.requireNonNull(id, "User ID cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(createdAt, "Created date cannot be null");
        Objects.requireNonNull(updatedAt, "Updated date cannot be null");

        if (firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (updatedAt.isBefore(createdAt)){
            throw new IllegalArgumentException("Updated date cannot be before created date");
        }

        this.id = id;
        this.email = email;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber != null ? phoneNumber.trim() : null;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ==================== Business Methods ====================

    /*
     * Activates the user.
     *
     * @throws IllegalStateException if the user is deleted
     */

    public void activate () {
        if (this.status == UserStatus.DELETED){
            throw new IllegalStateException("Cannot activate a deleted user");
        }
        this.status = UserStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Deactivates the user (temporary suspension).
     */
    public void deactivate () {
        if (this.status == UserStatus.DELETED){
            throw new IllegalStateException("Cannot deactivate a deleted user");
        }
        this.status = UserStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Soft-deletes the user.
     * This is irreversible.
     */
    public void delete (){
        this.status = UserStatus.DELETED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Updates user profile information.
     */
    public void updateProfile (String firstName, String lastName, String phoneNumber){
        if (this.status == UserStatus.DELETED){
            throw new IllegalArgumentException("Cannot update a deleted user");
        }

        if (firstName == null || firstName.isBlank()){
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (lastName == null || lastName.isBlank()){
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber != null ? phoneNumber.trim() : null;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Checks if the user is active.
     */
    public boolean isActive(){
        return this.status == UserStatus.ACTIVE;
    }

    /**
     * Checks if the user is deleted.
     */
    public boolean isDeleted(){
        return this.status == UserStatus.DELETED;
    }

    /**
     * Gets the full name of the user.
     */
    public String getFullName(){
        return firstName + " " + lastName;
    }

    // ==================== Object Methods ====================
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
