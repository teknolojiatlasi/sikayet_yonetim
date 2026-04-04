package com.company.sikayet.log.entity;

import com.company.sikayet.common.base.BaseEntity;
import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.sikayet.entity.Sikayet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sikayet_loglari")
public class SikayetLogu extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_id", nullable = false)
    private Sikayet sikayet;

    @Column(name = "islem_tipi", nullable = false, length = 100)
    private String islemTipi;

    @Column(name = "mesaj", nullable = false, length = 500)
    private String mesaj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;

    @Column(name = "islem_tarihi", nullable = false)
    private LocalDateTime islemTarihi;
}
