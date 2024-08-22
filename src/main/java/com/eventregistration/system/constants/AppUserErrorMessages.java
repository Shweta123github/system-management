package com.eventregistration.system.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AppUserErrorMessages {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Username already exists"),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Event not found"),
    SUCCESSFULLY_ADDED(HttpStatus.OK, "Successfully added"),
    SUCCESSFULLY_DELETED(HttpStatus.OK, "Successfully deleted"),
    SUCCESSFULLY_UPDATED(HttpStatus.OK, "Successfully updated"),
    GENERAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");

    HttpStatus status;
    String message;
}