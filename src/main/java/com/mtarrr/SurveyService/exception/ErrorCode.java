package com.mtarrr.SurveyService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNEXPECTED_ERROR("Unexpected error", "0001"),
    NO_HANDLER_FOUND("No Handler Found", "0002"),
    MALFORMED_JSON_REQUEST("Malformed JSON Request", "0003"),
    ENTITY_NOT_FOUND("Entity not found", "0004"),
    SURVEY_VALIDATION("Incorrect survey form. ", "0005"),
    USER_VALIDATION("Incorrect user form. ", "0006"),
    USER_EXIST("User already exist. Email: ", "0007"),
    NO_SUCH_TOKEN("No such token", "0008"),
    ALREADY_CONFIRMED("Email already confirmed", "0009"),
    TOKEN_EXPIRED("Token expired", "0010");

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    private String message;
    private String code;
}
