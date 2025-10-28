package com.ecommerce.user_service.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends DomainException {

    private final UUID userId;

    public UserNotFoundException (UUID userId){
        super("User not found with ID: " + userId);
        this.userId = userId;
    }

    public UserNotFoundException(String email){
        super("User not found with email: " + email);
        this.userId = null;
    }

    public UUID getUserId(){
        return userId;
    }
}
