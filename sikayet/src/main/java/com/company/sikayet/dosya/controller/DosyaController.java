package com.company.sikayet.dosya.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.dosya.dto.SikayetDosyaResponse;
import com.company.sikayet.dosya.service.DosyaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/complaints/{id}/files")
@RequiredArgsConstructor
public class DosyaController {

    private final DosyaService dosyaService;

    @GetMapping
    public BaseResponse<List<SikayetDosyaResponse>> findByComplaint(@PathVariable("id") Long id) {
        return BaseResponse.success(dosyaService.findBySikayetId(id), "Dosyalar hazirlandi.");
    }

    @PostMapping
    public BaseResponse<SikayetDosyaResponse> upload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        return BaseResponse.success(dosyaService.upload(id, file), "Dosya yuklendi.");
    }
}
