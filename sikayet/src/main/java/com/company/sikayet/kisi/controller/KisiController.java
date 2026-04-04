package com.company.sikayet.kisi.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.kisi.dto.KisiRequest;
import com.company.sikayet.kisi.dto.KisiResponse;
import com.company.sikayet.kisi.service.KisiService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService kisiService;

    @GetMapping
    public BaseResponse<List<KisiResponse>> findAll() {
        return BaseResponse.success(kisiService.findAll(), "Kisi listesi hazirlandi.");
    }

    @PostMapping
    public BaseResponse<KisiResponse> create(@Valid @RequestBody KisiRequest request) {
        return BaseResponse.success(kisiService.create(request), "Kisi olusturuldu.");
    }
}
