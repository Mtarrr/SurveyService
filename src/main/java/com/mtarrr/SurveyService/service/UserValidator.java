package com.mtarrr.SurveyService.service;

import com.mtarrr.SurveyService.exception.UserValidationException;
import com.mtarrr.SurveyService.model.entity.User;

import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    //валидация полей пользователя
    public boolean testUser(User user) {
        if (!user.getLastname().matches("^([А-Я]{1}[а-яё]{1,23})$")) {
            throw new UserValidationException("Lastname field is filled incorrect");
        } else if (!user.getFirstname().matches("^([А-Я]{1}[а-яё]{1,23})$")) {
            throw new UserValidationException("Firstname field is filled incorrect");
        } else if (!user.getMiddlename().matches("^([А-Я]{1}[а-яё]{1,23})$")) {
            throw new UserValidationException("Middlename field is filled incorrect");
        } else if (!user.getEmail().matches("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)")) {
            throw new UserValidationException("Email field is filled incorrect");
        } else if (user.getDateOfBirth() == null) {
            throw new UserValidationException("DateOfBirth field is filled incorrect. " +
                    "Pattern: yyyy-MM-DD");
        } else if (!user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
            throw new UserValidationException("Password field is filled incorrect. " +
                    "The password must consist of numbers and Latin letters of upper and lower case");
        }
        return true;
    }
}
