package com.mtarrr.SurveyService.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query(value = """
            select s from Survey s inner join User u\s
            on s.user.id = u.id\s
            where u.id = :id and (s.revoked = false)\s
            """)
    List<Survey> findAllValidSurveyByUser(Integer id);

    Optional<Survey> findByEmail(String email);
}
