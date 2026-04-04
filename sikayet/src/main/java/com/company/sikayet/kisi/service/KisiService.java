package com.company.sikayet.kisi.service;

import com.company.sikayet.kisi.dto.KisiRequest;
import com.company.sikayet.kisi.dto.KisiResponse;
import java.util.List;

public interface KisiService {

    List<KisiResponse> findAll();

    KisiResponse create(KisiRequest request);
}
