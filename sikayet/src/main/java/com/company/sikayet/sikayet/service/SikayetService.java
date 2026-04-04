package com.company.sikayet.sikayet.service;

import com.company.sikayet.sikayet.dto.SikayetAssignRequest;
import com.company.sikayet.sikayet.dto.SikayetCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import com.company.sikayet.sikayet.dto.SikayetResultCreateRequest;
import com.company.sikayet.sikayet.dto.SikayetStatusChangeRequest;
import java.util.List;

public interface SikayetService {

    List<SikayetListResponse> findAll();

    SikayetDetailResponse findById(Long id);

    SikayetDetailResponse create(SikayetCreateRequest request);

    SikayetDetailResponse assign(Long id, SikayetAssignRequest request);

    SikayetDetailResponse changeStatus(Long id, SikayetStatusChangeRequest request);

    SikayetDetailResponse createResult(Long id, SikayetResultCreateRequest request);
}
