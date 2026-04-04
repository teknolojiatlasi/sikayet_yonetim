package com.company.sikayet.sikayet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SikayetCreateRequest(
        @NotNull(message = "Kisi zorunludur.") Long kisiId,
        @NotNull(message = "Sikayet turu zorunludur.") Long sikayetTuruId,
        @NotBlank(message = "Konu zorunludur.") String konu,
        @NotBlank(message = "Aciklama zorunludur.") String aciklama,
        String oncelik) {
}
