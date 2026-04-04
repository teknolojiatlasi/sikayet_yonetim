package com.company.sikayet.dosya.repository;

import com.company.sikayet.dosya.entity.SikayetDosyasi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetDosyasiRepository extends JpaRepository<SikayetDosyasi, Long> {

    List<SikayetDosyasi> findBySikayetId(Long sikayetId);
}
