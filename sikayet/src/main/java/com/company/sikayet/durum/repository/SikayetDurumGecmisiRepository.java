package com.company.sikayet.durum.repository;

import com.company.sikayet.durum.entity.SikayetDurumGecmisi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetDurumGecmisiRepository extends JpaRepository<SikayetDurumGecmisi, Long> {

    List<SikayetDurumGecmisi> findBySikayetIdOrderByDegisimTarihiDesc(Long sikayetId);
}
