package com.company.sikayet.kullanici.dto;

import java.util.List;

public record KullaniciResponse(Long id, String kullaniciAdi, String email, String ad, String soyad, boolean active,
                                List<String> roller) {
}
