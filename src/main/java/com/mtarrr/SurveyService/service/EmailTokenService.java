package com.mtarrr.SurveyService.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.mtarrr.SurveyService.model.entity.EmailToken;
import com.mtarrr.SurveyService.repository.EmailTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailTokenService {
    //сервис токенов подтверждения
    private final EmailTokenRepository emailTokenRepository;

    public void saveConfirmationToken(EmailToken emailToken) {
        emailTokenRepository.save(emailToken);
    }

    public Optional<EmailToken> getToken(String token) {
        return emailTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return emailTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
