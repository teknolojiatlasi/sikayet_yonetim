package com.company.sikayet.audit;

import com.company.sikayet.sikayet.entity.Sikayet;
import com.company.sikayet.sikayet.entity.SikayetLogu;
import com.company.sikayet.sikayet.repository.SikayetLoguRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final SikayetLoguRepository sikayetLoguRepository;

    public void sikayetLogla(Sikayet sikayet, String islemTipi, String mesaj) {
        SikayetLogu log = new SikayetLogu();
        log.setSikayet(sikayet);
        log.setIslemTipi(islemTipi);
        log.setMesaj(mesaj);
        log.setIslemTarihi(LocalDateTime.now());
        sikayetLoguRepository.save(log);
    }
}
