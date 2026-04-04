package com.company.sikayet.kullanici.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record KullaniciCreateRequest(
        @NotBlank(message = "Kullanici adi zorunludur.") String kullaniciAdi,
        @Email(message = "Gecerli e-posta giriniz.") String email,
        @NotBlank(message = "Sifre zorunludur.") String sifre,
        @NotBlank(message = "Ad zorunludur.") String ad,
        @NotBlank(message = "Soyad zorunludur.") String soyad) {
}
