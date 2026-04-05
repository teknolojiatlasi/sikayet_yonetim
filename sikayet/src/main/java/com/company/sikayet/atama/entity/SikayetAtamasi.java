package com.company.sikayet.atama.entity;

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
@Table(name = "sikayet_atamalari")
public class SikayetAtamasi extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_id", nullable = false)
    private Sikayet sikayet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atanan_kullanici_id", nullable = false)
    private Kullanici atananKullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atayan_kullanici_id")
    private Kullanici atayanKullanici;

    @Column(name = "atama_tarihi", nullable = false)
    private LocalDateTime atamaTarihi;

    @Column(name = "bitis_tarihi")
    private LocalDateTime bitisTarihi;

    @Column(name = "aciklama", length = 255)
    private String aciklama;

    @Column(name = "aktif", nullable = false)
    private boolean aktif = true;
}
