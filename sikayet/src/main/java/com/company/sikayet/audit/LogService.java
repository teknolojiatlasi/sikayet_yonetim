package com.company.sikayet.audit;

import com.company.sikayet.log.entity.SikayetLogu;
import com.company.sikayet.log.repository.SikayetLoguRepository;
import com.company.sikayet.sikayet.entity.Sikayet;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final SikayetLoguRepository sikayetLoguRepository;

    public void log(Sikayet sikayet, String islemTipi, String mesaj) {
        SikayetLogu log = new SikayetLogu();
        log.setSikayet(sikayet);
        log.setIslemTipi(islemTipi);
        log.setMesaj(mesaj);
        log.setIslemTarihi(LocalDateTime.now());
        sikayetLoguRepository.save(log);
    }

    public List<SikayetLogu> getBySikayetId(Long sikayetId) {
        return sikayetLoguRepository.findBySikayetIdOrderByIslemTarihiDesc(sikayetId);
    }
}
