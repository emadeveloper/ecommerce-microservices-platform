package com.ecommerce.user_service.domain.model;

import com.ecommerce.user_service.domain.valueobject.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User domain entity.
 */

@DisplayName("User Entity Tests")
class UserTest {

    @Test
    @DisplayName("Should create a valid user with all fields")
    void shouldCreateValidUserWithAllFields() {
        //Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "+12345678";
        UserStatus status = UserStatus.ACTIVE;
        LocalDateTime now = LocalDateTime.now();

        // When
        User user = new User(id, email, firstName, lastName, phoneNumber, status, now, now);

        // Then
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("+12345678", user.getPhoneNumber());
        assertEquals(UserStatus.ACTIVE, user.getStatus());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    @DisplayName("Should create user without phone number")
    void shouldCreateUserWithoutPhoneNumber() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When
        User user = new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, now, now);

        // Then
        assertNull(user.getPhoneNumber());
    }

    @Test
    @DisplayName("Should trim first name and last name")
    void shouldTrimNames() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When
        User user = new User(id, email, "  John  ", "  Doe  ", null, UserStatus.ACTIVE, now, now);

        // Then
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    @DisplayName("Should throw exception when id is null")
    void shouldThrowExceptionWhenIdIsNull() {
        // Given
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When & Then
        assertThrows(
                NullPointerException.class,
                () -> new User(null, email, "John", "Doe", null, UserStatus.ACTIVE, now, now)
        );
    }

    @Test
    @DisplayName("Should throw exception when email is null")
    void shouldThrowExceptionWhenEmailIsNull() {
        // Given
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        // When & Then
        assertThrows(
                NullPointerException.class,
                () -> new User(id, null, "John", "Doe", null, UserStatus.ACTIVE, now, now)
        );
    }

    @Test
    @DisplayName("Should throw exception when firstName is null")
    void shouldThrowExceptionWhenFirstNameIsNull() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, email, null, "Doe", null, UserStatus.ACTIVE, now, now)
        );

        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when firstName is blank")
    void shouldThrowExceptionWhenFirstNameIsBlank() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, email, "   ", "Doe", null, UserStatus.ACTIVE, now, now)
        );

        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when lastName is null")
    void shouldThrowExceptionWhenLastNameIsNull() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, email, "John", null, null, UserStatus.ACTIVE, now, now)
        );

        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when updatedAt is before createdAt")
    void shouldThrowExceptionWhenUpdatedAtIsBeforeCreatedAt() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = createdAt.minusDays(1);

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, createdAt, updatedAt)
        );

        assertEquals("Updated date cannot be before created date", exception.getMessage());
    }

    @Test
    @DisplayName("Should activate inactive user")
    void shouldActivateInactiveUser() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.INACTIVE, now, now);

        // When
        user.activate();

        // Then
        assertEquals(UserStatus.ACTIVE, user.getStatus());
        assertTrue(user.getUpdatedAt().isAfter(now));
    }

    @Test
    @DisplayName("Should throw exception when activating deleted user")
    void shouldThrowExceptionWhenActivatingDeletedUser() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.DELETED, now, now);

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                user::activate
        );

        assertEquals("Cannot activate a deleted user", exception.getMessage());
    }

    @Test
    @DisplayName("Should deactivate active user")
    void shouldDeactivateActiveUser() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, now, now);

        // When
        user.deactivate();

        // Then
        assertEquals(UserStatus.INACTIVE, user.getStatus());
        assertTrue(user.getUpdatedAt().isAfter(now));
    }

    @Test
    @DisplayName("Should throw exception when deactivating deleted user")
    void shouldThrowExceptionWhenDeactivatingDeletedUser() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.DELETED, now, now);

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                user::deactivate
        );

        assertEquals("Cannot deactivate a deleted user", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete active user")
    void shouldDeleteActiveUser() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, now, now);

        // When
        user.delete();

        // Then
        assertEquals(UserStatus.DELETED, user.getStatus());
        assertTrue(user.getUpdatedAt().isAfter(now));
        assertTrue(user.isDeleted());
    }

    @Test
    @DisplayName("Should update user profile")
    void shouldUpdateUserProfile() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", "+1234567890", UserStatus.ACTIVE, now, now);

        // When
        user.updateProfile("Jane", "Smith", "+9876543210");

        // Then
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("+9876543210", user.getPhoneNumber());
        assertTrue(user.getUpdatedAt().isAfter(now));
    }

    @Test
    @DisplayName("Should throw exception when updating deleted user profile")
    void shouldThrowExceptionWhenUpdatingDeletedUserProfile() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.DELETED, now, now);

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> user.updateProfile("Jane", "Smith", null)
        );

        assertEquals("Cannot update a deleted user", exception.getMessage());
    }

    @Test
    @DisplayName("Should return true when user is active")
    void shouldReturnTrueWhenUserIsActive() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, now, now);

        // When & Then
        assertTrue(user.isActive());
        assertFalse(user.isDeleted());
    }

    @Test
    @DisplayName("Should return full name")
    void shouldReturnFullName() {
        // Given
        UUID id = UUID.randomUUID();
        Email email = new Email("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        User user = new User(id, email, "John", "Doe", null, UserStatus.ACTIVE, now, now);

        // When
        String fullName = user.getFullName();

        // Then
        assertEquals("John Doe", fullName);
    }

    @Test
    @DisplayName("Should consider users equal if they have same id")
    void shouldConsiderUsersEqualIfSameId() {
        // Given
        UUID id = UUID.randomUUID();
        Email email1 = new Email("test1@example.com");
        Email email2 = new Email("test2@example.com");
        LocalDateTime now = LocalDateTime.now();

        User user1 = new User(id, email1, "John", "Doe", null, UserStatus.ACTIVE, now, now);
        User user2 = new User(id, email2, "Jane", "Smith", null, UserStatus.INACTIVE, now, now);

        // Then
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
