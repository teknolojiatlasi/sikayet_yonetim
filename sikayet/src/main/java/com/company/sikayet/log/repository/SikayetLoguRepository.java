package com.company.sikayet.log.repository;

import com.company.sikayet.log.entity.SikayetLogu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetLoguRepository extends JpaRepository<SikayetLogu, Long> {

    List<SikayetLogu> findBySikayetIdOrderByIslemTarihiDesc(Long sikayetId);
}
