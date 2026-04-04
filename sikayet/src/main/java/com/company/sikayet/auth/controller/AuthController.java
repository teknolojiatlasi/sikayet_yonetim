package com.company.sikayet.auth.controller;

import com.company.sikayet.auth.dto.AuthResponse;
import com.company.sikayet.auth.dto.LoginRequest;
import com.company.sikayet.auth.dto.MeResponse;
import com.company.sikayet.auth.dto.RefreshTokenRequest;
import com.company.sikayet.auth.service.AuthService;
import com.company.sikayet.common.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return BaseResponse.success(authService.login(request), "Giris basarili.");
    }

    @PostMapping("/refresh")
    public BaseResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return BaseResponse.success(authService.refresh(request), "Token yenilendi.");
    }

    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        return BaseResponse.success("OK", "Cikis basarili.");
    }

    @GetMapping("/me")
    public BaseResponse<MeResponse> me(Authentication authentication) {
        return BaseResponse.success(authService.me(authentication), "Profil bilgisi hazirlandi.");
    }
}
