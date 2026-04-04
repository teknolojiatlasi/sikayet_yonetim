package com.company.sikayet.kullanici.service;

import com.company.sikayet.kullanici.dto.KullaniciCreateRequest;
import com.company.sikayet.kullanici.dto.KullaniciResponse;
import java.util.List;

public interface KullaniciService {

    List<KullaniciResponse> findAll();

    KullaniciResponse create(KullaniciCreateRequest request);
}
