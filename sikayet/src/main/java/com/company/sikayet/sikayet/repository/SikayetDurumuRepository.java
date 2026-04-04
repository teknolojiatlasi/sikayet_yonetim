package com.company.sikayet.sikayet.repository;

import com.company.sikayet.sikayet.entity.SikayetDurumu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetDurumuRepository extends JpaRepository<SikayetDurumu, Long> {

    Optional<SikayetDurumu> findByKod(String kod);
}
