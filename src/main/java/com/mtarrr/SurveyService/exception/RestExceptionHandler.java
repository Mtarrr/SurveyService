package com.mtarrr.SurveyService.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions() {
        return new ResponseEntity<>(new ApiError(ErrorCode.UNEXPECTED_ERROR.getMessage(), ErrorCode.UNEXPECTED_ERROR.getCode()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), ErrorCode.NO_HANDLER_FOUND.getCode()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(new ApiError(ErrorCode.MALFORMED_JSON_REQUEST.getMessage(), ErrorCode.MALFORMED_JSON_REQUEST.getCode()), status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundEx(UserNotFoundException e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.ENTITY_NOT_FOUND.getMessage() + e.getMessage(), ErrorCode.ENTITY_NOT_FOUND.getCode()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SurveyValidationException.class)
    protected ResponseEntity<Object> handleSurveyValidationEx(SurveyValidationException e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.SURVEY_VALIDATION.getMessage() + e.getMessage(), ErrorCode.SURVEY_VALIDATION.getCode()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<Object> handleUserValidationEx(UserValidationException e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.USER_VALIDATION.getMessage() + e.getMessage(), ErrorCode.USER_VALIDATION.getCode()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

}
