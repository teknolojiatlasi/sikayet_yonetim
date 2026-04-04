package com.company.sikayet.sikayet.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.sikayet.dto.SikayetAssignRequest;
import com.company.sikayet.sikayet.dto.SikayetCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import com.company.sikayet.sikayet.dto.SikayetResultCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetStatusChangeRequest;
import com.company.sikayet.sikayet.service.SikayetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class SikayetController {

    private final SikayetService sikayetService;

    @GetMapping
    public BaseResponse<List<SikayetListResponse>> findAll() {
        return BaseResponse.success(sikayetService.findAll(), "Sikayet listesi hazirlandi.");
    }

    @GetMapping("/{id}")
    public BaseResponse<SikayetDetailResponse> findById(@PathVariable Long id) {
        return BaseResponse.success(sikayetService.findById(id), "Sikayet detayi hazirlandi.");
    }

    @PostMapping
    public BaseResponse<SikayetDetailResponse> create(@Valid @RequestBody SikayetCreateRequest request) {
        return BaseResponse.success(sikayetService.create(request), "Sikayet olusturuldu.");
    }

    @PostMapping("/{id}/assign")
    public BaseResponse<SikayetDetailResponse> assign(@PathVariable Long id, @Valid @RequestBody SikayetAssignRequest request) {
        return BaseResponse.success(sikayetService.assign(id, request), "Sikayet atandi.");
    }

    @PostMapping("/{id}/change-status")
    public BaseResponse<SikayetDetailResponse> changeStatus(
            @PathVariable Long id,
            @Valid @RequestBody SikayetStatusChangeRequest request) {
        return BaseResponse.success(sikayetService.changeStatus(id, request), "Durum guncellendi.");
    }

    @PostMapping("/{id}/result")
    public BaseResponse<SikayetDetailResponse> createResult(
            @PathVariable Long id,
            @Valid @RequestBody SikayetResultCreateRequest request) {
        return BaseResponse.success(sikayetService.createResult(id, request), "Sonuc kaydedildi.");
    }
}
