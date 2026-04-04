package com.company.sikayet.durum.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.durum.repository.SikayetDurumGecmisiRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complaints/{id}/status-history")
@RequiredArgsConstructor
public class SikayetDurumGecmisiController {

    private final SikayetDurumGecmisiRepository sikayetDurumGecmisiRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public BaseResponse<List<String>> findHistory(@PathVariable("id") Long id) {
        return BaseResponse.success(
                sikayetDurumGecmisiRepository.findBySikayetIdOrderByDegisimTarihiDesc(id).stream()
                        .map(item -> item.getYeniDurum().getAd())
                        .toList(),
                "Durum gecmisi hazirlandi.");
    }
}
