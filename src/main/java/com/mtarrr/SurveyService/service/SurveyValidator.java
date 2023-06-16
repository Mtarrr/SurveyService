package com.mtarrr.SurveyService.service;

import com.mtarrr.SurveyService.exception.SurveyValidationException;
import com.mtarrr.SurveyService.model.entity.Survey;

import org.springframework.stereotype.Service;

@Service
public class SurveyValidator {
    //валидация формы опроса
    public boolean testSurvey(Survey survey) {
        if (!survey.getFavoriteFood().matches("^([а-яё]{1,23})$")) {
            throw new SurveyValidationException("FavoriteFood field is filled incorrect");
        } else if (!survey.getFavoriteColor().matches("^(#([A-Z]|[0-9]){1,6})$")) {
            throw new SurveyValidationException("FavoriteColor field is filled incorrect" +
                    "Pattern #000000 or #FFFFFF");
        } else if (survey.getFavoriteSong() == null) {
            throw new SurveyValidationException("FavoriteSong field is filled incorrect");
        } else if (survey.getFavoriteDate() == null) {
            throw new SurveyValidationException("FavoriteDate field is filled incorrect");
        } else if (survey.getFavoriteNumber() == null) {
            throw new SurveyValidationException("FavoriteNumber field is filled incorrect");
        }
        return true;
    }
}
