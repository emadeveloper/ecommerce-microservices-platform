package com.ecommerce.user_service.domain.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Email value object.
 */

@DisplayName("Email Value Objects Tests")
class EmailTest {

    @Test
    @DisplayName("Should create a valid email with lowercase")
    void shouldCreateValidEmail() {
        // Given
        String emailAddress = "test@example.com";

        // When
        Email email = new Email(emailAddress);

        // Then
        assertNotNull(email);
        assertEquals("test@example.com" , email.getAddress());
    }

    @Test
    @DisplayName("Should normalize email to lowercase")
    void shouldNormalizeToLowecase() {
        // Given
        String emailAddress = "TeSt@ExaMpLe.Com";

        // When
        Email email = new Email(emailAddress);

        // Then
        assertEquals("test@example.com", email.getAddress());
    }

    @Test
    @DisplayName("Should trim whitespace from email")
    void shouldTrimWhitespaceFromEmail(){
        // Given
        String emailAddress = " test@example.com ";

        // When
        Email email = new Email(emailAddress);

        //Then
        assertEquals("test@example.com", email.getAddress());
    }

    @Test
    @DisplayName("Should throw exception for null email")
    void shouldThrowExceptionForNullEmail(){
        // When & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email(null)
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for empty email")
    void shouldThrowExceptionForEmptyEmail(){
        // When & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("")
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for blank email")
    void shouldThrowExceptionForBlankEmail(){
        // When & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("   ")
        );
        assertEquals("Email address cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for invalid email format - missing @")
    void shouldThrowExceptionForInvalidEmailMissingAt() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("testexample.com")
        );

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    @DisplayName("Should throw exception for invalid email format - missing domain")
    void shouldThrowExceptionForInvalidEmailMissingDomain (){
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("test@")
        );

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    @DisplayName("Should throw exception for invalid email format - missing local part")
    void shouldThrowExceptionForInvalidEmailFormatMissingLocalPart() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("@example.com")
        );

        assertTrue(exception.getMessage().contains("Invalid email format"));
    }

    @Test
    @DisplayName("Should consider two emails equals if they have same address")
    void shouldConsiderEmailsEquals() {
        // Given
        Email email1 = new Email("test@example.com");
        Email email2 = new Email("TEST@EXAMPLE.COM");

        // Then
        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    @DisplayName("Should return email address in toString")
    void shouldReturnAddressInToString() {
        // Given
        Email emailAddress = new Email("test@example.com");

        // When
        String result = emailAddress.toString();

        // Then
        assertEquals("test@example.com", result);
    }
}
