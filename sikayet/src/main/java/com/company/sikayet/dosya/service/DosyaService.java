package com.company.sikayet.dosya.service;

import com.company.sikayet.dosya.dto.SikayetDosyaResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface DosyaService {

    SikayetDosyaResponse upload(Long sikayetId, MultipartFile file);

    List<SikayetDosyaResponse> findBySikayetId(Long sikayetId);
}
