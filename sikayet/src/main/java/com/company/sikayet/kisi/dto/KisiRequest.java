package com.company.sikayet.kisi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record KisiRequest(
        @NotBlank(message = "Ad zorunludur.") String ad,
        @NotBlank(message = "Soyad zorunludur.") String soyad,
        String telefon,
        @Email(message = "Gecerli e-posta giriniz.") String email) {
}
