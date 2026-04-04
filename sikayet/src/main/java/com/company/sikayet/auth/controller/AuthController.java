package com.company.sikayet.auth.controller;

import com.company.sikayet.auth.dto.AuthResponse;
import com.company.sikayet.auth.dto.LoginRequest;
import com.company.sikayet.auth.service.AuthService;
import com.company.sikayet.common.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/health")
    public BaseResponse<String> health() {
        return BaseResponse.success("OK", "Servis ayakta.");
    }
}
