package com.company.sikayet.sikayet.dto;

import java.time.LocalDateTime;

public record SikayetListResponse(
        Long id,
        String sikayetNo,
        String konu,
        String kisiAdSoyad,
        String sikayetTuru,
        String durum,
        String oncelik,
        LocalDateTime createdAt) {
}
