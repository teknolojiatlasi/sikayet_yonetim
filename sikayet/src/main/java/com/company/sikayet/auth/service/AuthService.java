package com.company.sikayet.auth.service;

import com.company.sikayet.auth.dto.AuthResponse;
import com.company.sikayet.auth.dto.LoginRequest;
import com.company.sikayet.auth.dto.MeResponse;
import com.company.sikayet.auth.dto.RefreshTokenRequest;
import com.company.sikayet.security.jwt.JwtService;
import com.company.sikayet.security.model.SecurityUserDetails;
import com.company.sikayet.security.service.CustomUserDetailsService;
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
    private final CustomUserDetailsService customUserDetailsService;

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        return toResponse(userDetails);
    }

    public AuthResponse refresh(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.refreshToken());
        SecurityUserDetails userDetails = (SecurityUserDetails) customUserDetailsService.loadUserByUsername(username);
        return toResponse(userDetails);
    }

    public MeResponse me(Authentication authentication) {
        SecurityUserDetails principal = (SecurityUserDetails) authentication.getPrincipal();
        return new MeResponse(
                principal.username(),
                principal.getAuthorities().stream().map(authority -> authority.getAuthority()).toList());
    }

    private AuthResponse toResponse(SecurityUserDetails userDetails) {
        return new AuthResponse(
                jwtService.generateAccessToken(userDetails),
                jwtService.generateRefreshToken(userDetails),
                "Bearer",
                userDetails.username(),
                userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).toList());
    }
}
