package com.mtarrr.SurveyService.exception;

public class UserExistException extends IllegalStateException{
    public UserExistException(String message) {
        super(message);
    }
}
