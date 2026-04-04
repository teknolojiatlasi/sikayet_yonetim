package com.company.sikayet.sikayet.repository;

import com.company.sikayet.sikayet.entity.Sikayet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SikayetRepository extends JpaRepository<Sikayet, Long>, JpaSpecificationExecutor<Sikayet> {

    @Override
    @EntityGraph(attributePaths = {"kisi", "sikayetTuru", "sikayetDurumu"})
    java.util.List<Sikayet> findAll();
}
