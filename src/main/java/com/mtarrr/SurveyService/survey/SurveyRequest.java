package com.mtarrr.SurveyService.survey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SurveyRequest {
    //описание запроса на заполнение формы
    private final String favoriteFood;
    private final String favoriteColor;
    private final String favoriteSong;
    private final Date favoriteDate;
    private final Long favoriteNumber;
}

