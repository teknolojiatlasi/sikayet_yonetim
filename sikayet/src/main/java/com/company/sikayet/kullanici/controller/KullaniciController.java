package com.company.sikayet.kullanici.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.kullanici.dto.KullaniciCreateRequest;
import com.company.sikayet.kullanici.dto.KullaniciResponse;
import com.company.sikayet.kullanici.service.KullaniciService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @GetMapping
    public BaseResponse<List<KullaniciResponse>> findAll() {
        return BaseResponse.success(kullaniciService.findAll(), "Kullanici listesi hazirlandi.");
    }

    @PostMapping
    public BaseResponse<KullaniciResponse> create(@Valid @RequestBody KullaniciCreateRequest request) {
        return BaseResponse.success(kullaniciService.create(request), "Kullanici olusturuldu.");
    }
}
