package com.company.sikayet.sikayet.dto;

import jakarta.validation.constraints.NotBlank;

public record SikayetStatusChangeRequest(
        @NotBlank(message = "Durum kodu zorunludur.") String durumKodu,
        String aciklama) {
}
