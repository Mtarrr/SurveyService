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
    USER_VALIDATION("Incorrect user form. ", "0005");

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    private String message;
    private String code;
}
