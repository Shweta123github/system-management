package com.eventregistration.system.exception;

import com.eventregistration.system.constants.AppUserErrorMessages;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    private final AppUserErrorMessages errorMessage;

    public ResourceNotFoundException(AppUserErrorMessages errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return errorMessage.getStatus();
    }
}
