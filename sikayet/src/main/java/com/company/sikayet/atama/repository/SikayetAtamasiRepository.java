package com.company.sikayet.atama.repository;

import com.company.sikayet.atama.entity.SikayetAtamasi;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetAtamasiRepository extends JpaRepository<SikayetAtamasi, Long> {

    Optional<SikayetAtamasi> findBySikayetIdAndAktifTrue(Long sikayetId);

    List<SikayetAtamasi> findBySikayetIdOrderByAtamaTarihiDesc(Long sikayetId);
}
