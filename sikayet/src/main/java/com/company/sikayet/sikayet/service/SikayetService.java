package com.company.sikayet.sikayet.service;

import com.company.sikayet.sikayet.dto.SikayetCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import java.util.List;

public interface SikayetService {

    SikayetDetailResponse create(SikayetCreateRequest request);

    List<SikayetListResponse> findAll();

    SikayetDetailResponse findById(Long id);
}
