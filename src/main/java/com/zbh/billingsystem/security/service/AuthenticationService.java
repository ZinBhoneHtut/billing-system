package com.zbh.billingsystem.security.service;

import com.zbh.billingsystem.dto.AuthRequest;
import com.zbh.billingsystem.dto.AuthResponse;
import com.zbh.billingsystem.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String accessToken = jwtService.generateJwtToken(authentication);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .statusMessage("Login is successful")
                .build();
    }

}

