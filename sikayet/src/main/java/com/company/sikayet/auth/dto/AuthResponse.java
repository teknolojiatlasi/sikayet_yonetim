package com.company.sikayet.auth.dto;

import java.util.List;

public record AuthResponse(String accessToken, String tokenType, String username, List<String> authorities) {
}
