package com.company.sikayet.sikayet.service.impl;

import com.company.sikayet.audit.AuditService;
import com.company.sikayet.exception.ResourceNotFoundException;
import com.company.sikayet.kisi.entity.Kisi;
import com.company.sikayet.kisi.repository.KisiRepository;
import com.company.sikayet.sikayet.dto.SikayetCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import com.company.sikayet.sikayet.entity.Sikayet;
import com.company.sikayet.sikayet.entity.SikayetDurumKodu;
import com.company.sikayet.sikayet.entity.SikayetDurumu;
import com.company.sikayet.sikayet.entity.SikayetTuru;
import com.company.sikayet.sikayet.mapper.SikayetMapper;
import com.company.sikayet.sikayet.repository.SikayetDurumuRepository;
import com.company.sikayet.sikayet.repository.SikayetRepository;
import com.company.sikayet.sikayet.repository.SikayetTuruRepository;
import com.company.sikayet.sikayet.service.SikayetService;
import java.time.Year;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SikayetServiceImpl implements SikayetService {

    private final SikayetRepository sikayetRepository;
    private final KisiRepository kisiRepository;
    private final SikayetTuruRepository sikayetTuruRepository;
    private final SikayetDurumuRepository sikayetDurumuRepository;
    private final SikayetMapper sikayetMapper;
    private final AuditService auditService;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_CREATE')")
    public SikayetDetailResponse create(SikayetCreateRequest request) {
        Kisi kisi = kisiRepository.findById(request.kisiId())
                .orElseThrow(() -> new ResourceNotFoundException("Kisi bulunamadi."));
        SikayetTuru sikayetTuru = sikayetTuruRepository.findById(request.sikayetTuruId())
                .orElseThrow(() -> new ResourceNotFoundException("Sikayet turu bulunamadi."));
        SikayetDurumu durum = sikayetDurumuRepository.findByKod(SikayetDurumKodu.YENI.name())
                .orElseThrow(() -> new ResourceNotFoundException("Baslangic durumu bulunamadi."));

        Sikayet sikayet = new Sikayet();
        sikayet.setSikayetNo(generateComplaintNo());
        sikayet.setKisi(kisi);
        sikayet.setSikayetTuru(sikayetTuru);
        sikayet.setSikayetDurumu(durum);
        sikayet.setKonu(request.konu());
        sikayet.setAciklama(request.aciklama());
        sikayet.setOncelik(request.oncelik());

        Sikayet saved = sikayetRepository.save(sikayet);
        auditService.sikayetLogla(saved, "SIKAYET_OLUSTURULDU", "Yeni sikayet kaydi acildi.");
        return sikayetMapper.toDetailResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public List<SikayetListResponse> findAll() {
        return sikayetRepository.findAll().stream().map(sikayetMapper::toListResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public SikayetDetailResponse findById(Long id) {
        Sikayet sikayet = sikayetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sikayet bulunamadi."));
        return sikayetMapper.toDetailResponse(sikayet);
    }

    private String generateComplaintNo() {
        return "SY-" + Year.now().getValue() + "-" + System.currentTimeMillis();
    }
}
