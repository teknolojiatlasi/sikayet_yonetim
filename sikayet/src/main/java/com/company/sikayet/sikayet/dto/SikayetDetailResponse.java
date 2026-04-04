package com.company.sikayet.sikayet.dto;

import java.time.LocalDateTime;

public record SikayetDetailResponse(
        Long id,
        String sikayetNo,
        String konu,
        String aciklama,
        String kisiAdSoyad,
        String sikayetTuru,
        String durum,
        String oncelik,
        LocalDateTime createdAt) {
}
