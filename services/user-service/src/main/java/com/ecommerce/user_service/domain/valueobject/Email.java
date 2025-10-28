package com.ecommerce.user_service.domain.valueobject;


import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object representing an email address.
 * This is immutable and ensures email validity through the constructor.
 * Two emails are equal if they have the same address (case-insensitive).
 */
public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String address;

    /**
     * Creates a new Email value object.
     *
     * @param address the email address
     * @throws IllegalArgumentException if the email is invalid
     */

    public Email (String address){
        if(address == null || address.isBlank()){
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }

        String normalized = address.trim().toLowerCase();

        if(!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + address);
        }

        this.address = normalized;
    }

    public String getAddress(){
        return address;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode(){
        return Objects.hash(address);
    }

    @Override
    public String toString(){
        return address;
    }

}
