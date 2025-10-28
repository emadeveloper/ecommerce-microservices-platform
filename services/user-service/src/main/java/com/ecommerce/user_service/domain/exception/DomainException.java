package com.ecommerce.user_service.domain.exception;

/**
 * Base exception for all domain-related exceptions.
 * Domain exceptions represents business rules violations.
 */

public abstract class DomainException extends RuntimeException {

    protected DomainException(String message){
        super(message);
    }

    protected DomainException(String message, Throwable cause){
        super(message, cause);
    }
}