package com.company.sikayet.sikayet.repository;

import com.company.sikayet.sikayet.entity.SikayetLogu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SikayetLoguRepository extends JpaRepository<SikayetLogu, Long> {

    List<SikayetLogu> findBySikayetIdOrderByIslemTarihiDesc(Long sikayetId);
}
