package com.mtarrr.SurveyService.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    //запрос на регистрацию

    private String firstname;
    private String lastname;
    private String middlename;
    private Date dateOfBirth;
    private String email;
    private String password;
}
