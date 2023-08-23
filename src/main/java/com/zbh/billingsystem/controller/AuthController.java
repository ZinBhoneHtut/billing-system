package com.zbh.billingsystem.controller;

import com.zbh.billingsystem.dto.AuthRequest;
import com.zbh.billingsystem.dto.AuthResponse;
import com.zbh.billingsystem.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        log.trace("Inside login method");
        AuthResponse authResponse = authenticationService.authenticate(authRequest);
        log.info("Successfully login");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}