package com.mtarrr.SurveyService.controller;

import com.mtarrr.SurveyService.model.dto.SurveyRequest;
import com.mtarrr.SurveyService.model.entity.User;
import com.mtarrr.SurveyService.service.SurveyService;
import com.mtarrr.SurveyService.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/survey-service/")
@RequiredArgsConstructor
public class SurveyController {
    //контроллер формы
    private final SurveyService surveyService;
    private final UserService userService;

    //заполнение формы по SurveyRequest
    @PostMapping(path = "/form")
    public ResponseEntity<?> fillTheForm(@RequestBody SurveyRequest request, HttpServletRequest httpServletRequest) {
        surveyService.fillTheForm(request, httpServletRequest);
        String body = String.format("Форма отправлена пользователем %s", surveyService.extractUsername(httpServletRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    //получение пользователей
    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = userService.getAllUsers(request);
        return ResponseEntity.status(HttpStatus.OK).body(allUsers.stream().map(User::getUsername)
                .collect(Collectors.toList()));
    }
}
