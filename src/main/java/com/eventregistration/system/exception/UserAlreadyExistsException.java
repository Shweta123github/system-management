package com.eventregistration.system.exception;

import com.eventregistration.system.constants.AppUserErrorMessages;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException {
    private final AppUserErrorMessages errorMessage;

    public UserAlreadyExistsException(AppUserErrorMessages errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return errorMessage.getStatus();
    }
}
