package com.company.sikayet.sikayet.dto;

import jakarta.validation.constraints.NotBlank;

public record SikayetResultCreateRequest(@NotBlank(message = "Sonuc metni zorunludur.") String sonucMetin) {
}
