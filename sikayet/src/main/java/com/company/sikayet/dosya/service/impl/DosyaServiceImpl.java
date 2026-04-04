package com.company.sikayet.dosya.service.impl;

import com.company.sikayet.audit.LogService;
import com.company.sikayet.dosya.dto.SikayetDosyaResponse;
import com.company.sikayet.dosya.entity.SikayetDosyasi;
import com.company.sikayet.dosya.repository.SikayetDosyasiRepository;
import com.company.sikayet.dosya.service.DosyaService;
import com.company.sikayet.exception.BusinessException;
import com.company.sikayet.exception.ResourceNotFoundException;
import com.company.sikayet.sikayet.entity.Sikayet;
import com.company.sikayet.sikayet.repository.SikayetRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DosyaServiceImpl implements DosyaService {

    private static final Set<String> ALLOWED_TYPES = Set.of("application/pdf", "image/png", "image/jpeg");

    private final SikayetRepository sikayetRepository;
    private final SikayetDosyasiRepository sikayetDosyasiRepository;
    private final LogService logService;

    @Value("${app.file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DOSYA_UPLOAD')")
    public SikayetDosyaResponse upload(Long sikayetId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("Bos dosya yuklenemez.");
        }
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new BusinessException("Dosya boyutu limit asimi.");
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new BusinessException("Desteklenmeyen dosya tipi.");
        }

        Sikayet sikayet = sikayetRepository.findById(sikayetId)
                .orElseThrow(() -> new ResourceNotFoundException("Sikayet bulunamadi."));

        String generatedName = UUID.randomUUID() + "-" + sanitize(file.getOriginalFilename());
        Path root = Path.of(uploadDir).toAbsolutePath().normalize();
        Path target = root.resolve(generatedName).normalize();

        try {
            Files.createDirectories(root);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BusinessException("Dosya kaydedilemedi.");
        }

        SikayetDosyasi dosya = new SikayetDosyasi();
        dosya.setSikayet(sikayet);
        dosya.setOrijinalDosyaAdi(file.getOriginalFilename());
        dosya.setSistemDosyaAdi(generatedName);
        dosya.setDosyaYolu(target.toString());
        dosya.setMimeType(file.getContentType());
        dosya.setDosyaBoyutu(file.getSize());

        SikayetDosyasi saved = sikayetDosyasiRepository.save(dosya);
        logService.log(sikayet, "DOSYA_EKLENDI", "Dosya yuklendi: " + saved.getOrijinalDosyaAdi());
        return new SikayetDosyaResponse(saved.getId(), saved.getOrijinalDosyaAdi(), saved.getMimeType(), saved.getDosyaBoyutu());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public List<SikayetDosyaResponse> findBySikayetId(Long sikayetId) {
        return sikayetDosyasiRepository.findBySikayetId(sikayetId).stream()
                .map(item -> new SikayetDosyaResponse(item.getId(), item.getOrijinalDosyaAdi(), item.getMimeType(), item.getDosyaBoyutu()))
                .toList();
    }

    private String sanitize(String filename) {
        return filename == null ? "dosya" : filename.replace("..", "").replace("\\", "").replace("/", "");
    }
}
