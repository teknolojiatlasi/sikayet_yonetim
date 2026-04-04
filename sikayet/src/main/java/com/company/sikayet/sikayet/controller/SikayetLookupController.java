package com.company.sikayet.sikayet.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.sikayet.dto.SikayetLookupResponse;
import com.company.sikayet.sikayet.repository.SikayetDurumuRepository;
import com.company.sikayet.sikayet.repository.SikayetTuruRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SikayetLookupController {

    private final SikayetTuruRepository sikayetTuruRepository;
    private final SikayetDurumuRepository sikayetDurumuRepository;

    @GetMapping("/api/complaint-types")
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public BaseResponse<List<SikayetLookupResponse>> complaintTypes() {
        return BaseResponse.success(
                sikayetTuruRepository.findAll().stream().map(item -> new SikayetLookupResponse(item.getId(), item.getKod(), item.getAd())).toList(),
                "Sikayet turleri hazirlandi.");
    }

    @GetMapping("/api/complaint-statuses")
    @PreAuthorize("hasAuthority('SIKAYET_VIEW')")
    public BaseResponse<List<SikayetLookupResponse>> complaintStatuses() {
        return BaseResponse.success(
                sikayetDurumuRepository.findAll().stream().map(item -> new SikayetLookupResponse(item.getId(), item.getKod(), item.getAd())).toList(),
                "Sikayet durumlari hazirlandi.");
    }
}
