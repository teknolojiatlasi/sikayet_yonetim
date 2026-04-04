package com.company.sikayet.auth.service;

import com.company.sikayet.auth.dto.AuthResponse;
import com.company.sikayet.auth.dto.LoginRequest;
import com.company.sikayet.security.jwt.JwtService;
import com.company.sikayet.security.model.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        SecurityUserDetails principal = (SecurityUserDetails) authentication.getPrincipal();

        return new AuthResponse(
                jwtService.generateAccessToken(principal),
                "Bearer",
                principal.username(),
                principal.getAuthorities().stream().map(authority -> authority.getAuthority()).toList());
    }
}
