package com.company.sikayet.common.base;

import java.time.LocalDateTime;

public record BaseDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdBy,
        String updatedBy,
        boolean active) {
}
