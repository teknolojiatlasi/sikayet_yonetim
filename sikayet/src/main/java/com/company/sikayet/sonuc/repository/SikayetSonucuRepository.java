package com.company.sikayet.sonuc.repository;

import com.company.sikayet.sonuc.entity.SikayetSonucu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetSonucuRepository extends JpaRepository<SikayetSonucu, Long> {

    Optional<SikayetSonucu> findBySikayetId(Long sikayetId);
}
