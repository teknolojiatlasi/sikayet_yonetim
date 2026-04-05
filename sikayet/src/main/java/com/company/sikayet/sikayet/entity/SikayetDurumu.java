package com.company.sikayet.sikayet.entity;

import com.company.sikayet.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sikayet_durumlari")
public class SikayetDurumu extends BaseEntity {

    @Column(name = "kod", nullable = false, unique = true, length = 50)
    private String kod;

    @Column(name = "ad", nullable = false, length = 100)
    private String ad;

    @Column(name = "final_durum", nullable = false)
    private boolean finalDurum;

    @Column(name = "aciklama", length = 255)
    private String aciklama;

    @Column(name = "renk_kodu", length = 20)
    private String renkKodu;

    @Column(name = "sira_no", nullable = false)
    private Integer siraNo = 0;

    @Column(name = "sistem_mi", nullable = false)
    private boolean sistemMi = false;
}
