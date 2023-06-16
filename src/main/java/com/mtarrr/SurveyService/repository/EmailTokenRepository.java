package com.mtarrr.SurveyService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mtarrr.SurveyService.model.entity.EmailToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    Optional<EmailToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE EmailToken e " +
            "SET e.confirmedAt = ?2 " +
            "WHERE e.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
