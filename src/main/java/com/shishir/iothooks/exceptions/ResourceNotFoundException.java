package com.shishir.iothooks.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message+" not found");
    }
}
