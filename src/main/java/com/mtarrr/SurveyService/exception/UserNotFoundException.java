package com.mtarrr.SurveyService.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(", email: " + email);
    }
}
