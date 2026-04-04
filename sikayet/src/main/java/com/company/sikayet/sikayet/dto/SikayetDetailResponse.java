package com.company.sikayet.sikayet.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SikayetDetailResponse(
        Long id,
        String sikayetNo,
        String konu,
        String aciklama,
        String kisiAdSoyad,
        String sikayetTuru,
        String durum,
        String oncelik,
        String atananKullanici,
        String sonucMetni,
        LocalDateTime createdAt,
        List<String> loglar,
        List<String> durumGecmisi,
        List<String> dosyalar) {
}
