package com.company.sikayet.log.controller;

import com.company.sikayet.common.base.BaseResponse;
import com.company.sikayet.log.repository.SikayetLoguRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complaints/{id}/logs")
@RequiredArgsConstructor
public class SikayetLogController {

    private final SikayetLoguRepository sikayetLoguRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('LOG_VIEW')")
    public BaseResponse<List<String>> findLogs(@PathVariable("id") Long id) {
        return BaseResponse.success(
                sikayetLoguRepository.findBySikayetIdOrderByIslemTarihiDesc(id).stream().map(item -> item.getMesaj()).toList(),
                "Log kayitlari hazirlandi.");
    }
}
