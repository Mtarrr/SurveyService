package com.mtarrr.SurveyService.survey;

import com.mtarrr.SurveyService.config.JwtService;
import com.mtarrr.SurveyService.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class SurveyService {

    private static final String FORM_CREATED_LOG = "User : %s created form";
    private static final String FORM_UPDATED_LOG = "User : %s updated form";
    private static final String DISABLED_USER_LOG = "Disabled user : %s trying to create form";
    private final SurveyRepository surveyRepository;
    private final UserDetailsService userDetailsService;
    private final SurveyValidator surveyValidator;
    private final JwtService jwtService;

    public void saveForm(Survey survey) {
        surveyRepository.save(survey);
    }

    //получение модели из реквеста
    private Survey getModelFromRequest(SurveyRequest request, HttpServletRequest httpServletRequest) {
        return new Survey(
                request.getFavoriteFood(),
                request.getFavoriteColor(),
                request.getFavoriteSong(),
                request.getFavoriteDate(),
                request.getFavoriteNumber(),
                LocalDateTime.now(),
                (User) userDetailsService.loadUserByUsername(extractUsername(httpServletRequest))
        );
    }

    //обновление формы ппользователя
    private String updateForm(SurveyRequest request, HttpServletRequest httpServletRequest) {
        var survey = getModelFromRequest(request, httpServletRequest);
        surveyRepository.findByEmail(extractUsername(httpServletRequest));
        if (surveyValidator.testSurvey(survey)) {
            saveForm(survey);
        }
        return String.format(FORM_UPDATED_LOG, extractUsername(httpServletRequest));
    }

    //получение почты из хедера авторизации
    public String extractUsername(HttpServletRequest httpServletRequest) {
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("wrong request");
        }
        refreshToken = authHeader.substring(7);
        return jwtService.extractUsername(refreshToken);
    }

    //отзыв всех ранее заполненных форм
    private void revokeAllUserSurveys(User user) {
        var validUserSurveys = surveyRepository.findAllValidSurveyByUser(user.getId());
        if (validUserSurveys.isEmpty())
            return;
        validUserSurveys.forEach(token -> {
            token.setRevoked(true);
        });
        surveyRepository.saveAll(validUserSurveys);
    }

    //заполнение формы
    public String fillTheForm(SurveyRequest request, HttpServletRequest httpServletRequest) {
        log.info(request.toString());
        var userEmail = extractUsername(httpServletRequest);

        boolean formExists = surveyRepository.findByEmail(userEmail).isPresent();
        var user = userDetailsService.loadUserByUsername(userEmail);
        if (formExists) {
            log.info("form exists for email {}", userEmail);
            revokeAllUserSurveys((User) user);
            updateForm(request, httpServletRequest);
            return String.format(FORM_UPDATED_LOG, userEmail);
        }
        if (!userDetailsService.loadUserByUsername(userEmail).isEnabled()) {
            return String.format(DISABLED_USER_LOG, userEmail);
        }
        if (surveyValidator.testSurvey(getModelFromRequest(request, httpServletRequest))) {
            saveForm(getModelFromRequest(request, httpServletRequest));
        }
        return String.format(FORM_CREATED_LOG, userEmail);
    }
}
