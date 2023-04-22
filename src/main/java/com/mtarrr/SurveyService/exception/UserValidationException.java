package com.mtarrr.SurveyService.exception;

import javax.validation.ValidationException;

public class UserValidationException extends ValidationException {
    public UserValidationException(String message) {
        super(message);
    }
}
