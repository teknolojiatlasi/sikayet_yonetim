package com.company.sikayet.sikayet.service.impl;

import com.company.sikayet.atama.entity.SikayetAtamasi;
import com.company.sikayet.atama.repository.SikayetAtamasiRepository;
import com.company.sikayet.audit.LogService;
import com.company.sikayet.dosya.repository.SikayetDosyasiRepository;
import com.company.sikayet.durum.entity.SikayetDurumGecmisi;
import com.company.sikayet.durum.repository.SikayetDurumGecmisiRepository;
import com.company.sikayet.exception.BusinessException;
import com.company.sikayet.exception.ResourceNotFoundException;
import com.company.sikayet.kisi.entity.Kisi;
import com.company.sikayet.kisi.repository.KisiRepository;
import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.kullanici.repository.KullaniciRepository;
import com.company.sikayet.sikayet.dto.SikayetAssignRequest;
import com.company.sikayet.sikayet.dto.SikayetCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import com.company.sikayet.sikayet.dto.SikayetResultCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetStatusChangeRequest;
import com.company.sikayet.sikayet.entity.Sikayet;
import com.company.sikayet.sikayet.entity.SikayetDurumKodu;
import com.company.sikayet.sikayet.entity.SikayetDurumu;
import com.company.sikayet.sikayet.entity.SikayetTuru;
import com.company.sikayet.sikayet.repository.SikayetDurumuRepository;
import com.company.sikayet.sikayet.repository.SikayetRepository;
import com.company.sikayet.sikayet.repository.SikayetTuruRepository;
import com.company.sikayet.sikayet.service.SikayetService;
import com.company.sikayet.sonuc.entity.SikayetSonucu;
import com.company.sikayet.sonuc.repository.SikayetSonucuRepository;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SikayetServiceImpl implements SikayetService {

    private static final Map<String, Set<String>> VALID_TRANSITIONS = Map.of(
            "YENI", Set.of("ATANDI", "INCELEMEDE"),
            "ATANDI", Set.of("INCELEMEDE", "EK_BILGI_BEKLENIYOR"),
            "INCELEMEDE", Set.of("EK_BILGI_BEKLENIYOR", "SONUCLANDI"),
            "EK_BILGI_BEKLENIYOR", Set.of("INCELEMEDE", "SONUCLANDI"),
            "SONUCLANDI", Set.of("KAPATILDI"),
            "KAPATILDI", Set.of());

    private final SikayetRepository sikayetRepository;
    private final KisiRepository kisiRepository;
    private final KullaniciRepository kullaniciRepository;
    private final SikayetTuruRepository sikayetTuruRepository;
    private final SikayetDurumuRepository sikayetDurumuRepository;
    private final SikayetAtamasiRepository sikayetAtamasiRepository;
    private final SikayetDurumGecmisiRepository sikayetDurumGecmisiRepository;
    private final SikayetSonucuRepository sikayetSonucuRepository;
    private final com.company.sikayet.log.repository.SikayetLoguRepository sikayetLoguRepository;
    private final SikayetDosyasiRepository sikayetDosyasiRepository;
    private final LogService logService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public List<SikayetListResponse> findAll() {
        return sikayetRepository.findAll().stream()
                .map(this::toListResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public SikayetDetailResponse findById(Long id) {
        return toDetailResponse(findComplaint(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_CREATE')")
    public SikayetDetailResponse create(SikayetCreateRequest request) {
        Kisi kisi = kisiRepository.findById(request.kisiId())
                .orElseThrow(() -> new ResourceNotFoundException("Kisi bulunamadi."));
        SikayetTuru tur = sikayetTuruRepository.findById(request.sikayetTuruId())
                .orElseThrow(() -> new ResourceNotFoundException("Sikayet turu bulunamadi."));
        SikayetDurumu yeniDurum = findStatusByKod(SikayetDurumKodu.YENI.name());

        Sikayet sikayet = new Sikayet();
        sikayet.setSikayetNo("SY-" + Year.now().getValue() + "-" + System.currentTimeMillis());
        sikayet.setKisi(kisi);
        sikayet.setSikayetTuru(tur);
        sikayet.setSikayetDurumu(yeniDurum);
        sikayet.setKonu(request.konu());
        sikayet.setAciklama(request.aciklama());
        sikayet.setOncelik(request.oncelik());

        Sikayet saved = sikayetRepository.save(sikayet);
        writeHistory(saved, null, yeniDurum, "Kayit olusturuldu.");
        logService.log(saved, "SIKAYET_OLUSTURULDU", "Yeni sikayet kaydi acildi.");
        return toDetailResponse(saved);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_ASSIGN')")
    public SikayetDetailResponse assign(Long id, SikayetAssignRequest request) {
        Sikayet sikayet = findComplaint(id);
        Kullanici kullanici = kullaniciRepository.findById(request.kullaniciId())
                .orElseThrow(() -> new ResourceNotFoundException("Kullanici bulunamadi."));

        sikayetAtamasiRepository.findBySikayetIdAndAktifTrue(id).ifPresent(atama -> atama.setAktif(false));

        SikayetAtamasi atama = new SikayetAtamasi();
        atama.setSikayet(sikayet);
        atama.setAtananKullanici(kullanici);
        atama.setAtamaTarihi(LocalDateTime.now());
        sikayetAtamasiRepository.save(atama);

        SikayetDurumu atandi = findStatusByKod(SikayetDurumKodu.ATANDI.name());
        changeStatusInternal(sikayet, atandi, "Atama yapildi.");
        logService.log(sikayet, "SIKAYET_ATANDI", "Sikayet kullaniciya atandi.");
        return toDetailResponse(sikayet);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_STATUS_CHANGE')")
    public SikayetDetailResponse changeStatus(Long id, SikayetStatusChangeRequest request) {
        Sikayet sikayet = findComplaint(id);
        SikayetDurumu hedef = findStatusByKod(request.durumKodu());
        validateTransition(sikayet.getSikayetDurumu().getKod(), hedef.getKod());
        changeStatusInternal(sikayet, hedef, request.aciklama());
        logService.log(sikayet, "DURUM_DEGISTI", "Sikayet durumu degisti: " + hedef.getKod());
        return toDetailResponse(sikayet);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('SIKAYET_RESULT_CREATE')")
    public SikayetDetailResponse createResult(Long id, SikayetResultCreateRequest request) {
        Sikayet sikayet = findComplaint(id);
        if (sikayetSonucuRepository.findBySikayetId(id).isPresent()) {
            throw new BusinessException("Bu sikayet icin sonuc zaten girilmis.");
        }

        SikayetSonucu sonuc = new SikayetSonucu();
        sonuc.setSikayet(sikayet);
        sonuc.setSonucMetin(request.sonucMetin());
        sonuc.setSonuclanmaTarihi(LocalDateTime.now());
        sikayetSonucuRepository.save(sonuc);

        SikayetDurumu sonuclandi = findStatusByKod(SikayetDurumKodu.SONUCLANDI.name());
        changeStatusInternal(sikayet, sonuclandi, "Sonuc olusturuldu.");
        logService.log(sikayet, "SONUC_OLUSTURULDU", "Sikayet sonucu kaydedildi.");
        return toDetailResponse(sikayet);
    }

    private Sikayet findComplaint(Long id) {
        return sikayetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sikayet bulunamadi."));
    }

    private SikayetDurumu findStatusByKod(String kod) {
        return sikayetDurumuRepository.findByKod(kod)
                .orElseThrow(() -> new ResourceNotFoundException("Durum bulunamadi: " + kod));
    }

    private void validateTransition(String current, String next) {
        if (!VALID_TRANSITIONS.getOrDefault(current, Set.of()).contains(next) && !current.equals(next)) {
            throw new BusinessException("Gecersiz durum gecisi: " + current + " -> " + next);
        }
    }

    private void changeStatusInternal(Sikayet sikayet, SikayetDurumu yeniDurum, String aciklama) {
        SikayetDurumu eskiDurum = sikayet.getSikayetDurumu();
        sikayet.setSikayetDurumu(yeniDurum);
        writeHistory(sikayet, eskiDurum, yeniDurum, aciklama);
    }

    private void writeHistory(Sikayet sikayet, SikayetDurumu eskiDurum, SikayetDurumu yeniDurum, String aciklama) {
        SikayetDurumGecmisi gecmis = new SikayetDurumGecmisi();
        gecmis.setSikayet(sikayet);
        gecmis.setEskiDurum(eskiDurum);
        gecmis.setYeniDurum(yeniDurum);
        gecmis.setDegisimTarihi(LocalDateTime.now());
        gecmis.setAciklama(aciklama);
        sikayetDurumGecmisiRepository.save(gecmis);
    }

    private SikayetListResponse toListResponse(Sikayet entity) {
        return new SikayetListResponse(
                entity.getId(),
                entity.getSikayetNo(),
                entity.getKonu(),
                entity.getKisi().getAd() + " " + entity.getKisi().getSoyad(),
                entity.getSikayetTuru().getAd(),
                entity.getSikayetDurumu().getAd(),
                entity.getOncelik(),
                entity.getCreatedAt());
    }

    private SikayetDetailResponse toDetailResponse(Sikayet entity) {
        String atananKullanici = sikayetAtamasiRepository.findBySikayetIdAndAktifTrue(entity.getId())
                .map(SikayetAtamasi::getAtananKullanici)
                .map(k -> k.getAd() + " " + k.getSoyad())
                .orElse(null);
        String sonucMetni = sikayetSonucuRepository.findBySikayetId(entity.getId())
                .map(SikayetSonucu::getSonucMetin)
                .orElse(null);

        return new SikayetDetailResponse(
                entity.getId(),
                entity.getSikayetNo(),
                entity.getKonu(),
                entity.getAciklama(),
                entity.getKisi().getAd() + " " + entity.getKisi().getSoyad(),
                entity.getSikayetTuru().getAd(),
                entity.getSikayetDurumu().getAd(),
                entity.getOncelik(),
                atananKullanici,
                sonucMetni,
                entity.getCreatedAt(),
                sikayetLoguRepository.findBySikayetIdOrderByIslemTarihiDesc(entity.getId()).stream().map(log -> log.getMesaj()).toList(),
                sikayetDurumGecmisiRepository.findBySikayetIdOrderByDegisimTarihiDesc(entity.getId()).stream()
                        .map(history -> history.getYeniDurum().getAd()).toList(),
                sikayetDosyasiRepository.findBySikayetId(entity.getId()).stream().map(file -> file.getOrijinalDosyaAdi()).toList());
    }
}
