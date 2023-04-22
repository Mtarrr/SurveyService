package com.mtarrr.SurveyService.survey;

import com.mtarrr.SurveyService.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Survey {
    //описание сущности опроса
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    private Long id;
    private String favoriteFood;
    private String favoriteColor;
    private String favoriteSong;
    private Date favoriteDate;
    private Long favoriteNumber;
    private String email;
    private LocalDateTime updatedAt;

    public boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Survey(String favoriteFood,
                  String favoriteColor,
                  String favoriteSong,
                  Date favoriteDate,
                  Long favoriteNumber,
                  LocalDateTime updatedAt,
                  User user) {
        this.favoriteFood = favoriteFood;
        this.favoriteColor = favoriteColor;
        this.favoriteSong = favoriteSong;
        this.favoriteDate = favoriteDate;
        this.favoriteNumber = favoriteNumber;
        this.updatedAt = updatedAt;
        this.user = user;
        this.email = user.getEmail();
    }
}
