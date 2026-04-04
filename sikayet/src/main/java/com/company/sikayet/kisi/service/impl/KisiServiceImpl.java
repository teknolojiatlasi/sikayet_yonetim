package com.company.sikayet.kisi.service.impl;

import com.company.sikayet.kisi.dto.KisiRequest;
import com.company.sikayet.kisi.dto.KisiResponse;
import com.company.sikayet.kisi.entity.Kisi;
import com.company.sikayet.kisi.repository.KisiRepository;
import com.company.sikayet.kisi.service.KisiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public List<KisiResponse> findAll() {
        return kisiRepository.findAll().stream()
                .map(kisi -> new KisiResponse(kisi.getId(), kisi.getAd(), kisi.getSoyad(), kisi.getTelefon(), kisi.getEmail(), kisi.isActive()))
                .toList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_CREATE')")
    public KisiResponse create(KisiRequest request) {
        Kisi kisi = new Kisi();
        kisi.setAd(request.ad());
        kisi.setSoyad(request.soyad());
        kisi.setTelefon(request.telefon());
        kisi.setEmail(request.email());
        Kisi saved = kisiRepository.save(kisi);
        return new KisiResponse(saved.getId(), saved.getAd(), saved.getSoyad(), saved.getTelefon(), saved.getEmail(), saved.isActive());
    }
}
