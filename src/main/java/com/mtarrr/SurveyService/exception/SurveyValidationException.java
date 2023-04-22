package com.mtarrr.SurveyService.exception;

import javax.validation.ValidationException;

public class SurveyValidationException extends ValidationException {
    public SurveyValidationException(String message) {
        super(message);
    }
}
