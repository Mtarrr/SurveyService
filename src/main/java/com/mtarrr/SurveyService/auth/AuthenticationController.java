package com.mtarrr.SurveyService.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/survey-service/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    //контроллер аутентификации

    private final AuthenticationService service;

    //регистрация по RegisterRequest
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> admin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }

    //подтверджение адреса почты по токену
    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(service.confirmToken(token));
    }

    //аутентификация по AuthenticationRequest
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    //обновление jwt токена
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }


}
