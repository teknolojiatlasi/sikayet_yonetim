package com.company.sikayet.sikayet.entity;

import com.company.sikayet.common.base.BaseEntity;
import com.company.sikayet.kisi.entity.Kisi;
import com.company.sikayet.kullanici.entity.Kullanici;
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
@Table(name = "sikayetler")
public class Sikayet extends BaseEntity {

    @Column(name = "sikayet_no", nullable = false, unique = true, length = 50)
    private String sikayetNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kisi_id", nullable = false)
    private Kisi kisi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_turu_id", nullable = false)
    private SikayetTuru sikayetTuru;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_durumu_id", nullable = false)
    private SikayetDurumu sikayetDurumu;

    @Column(name = "konu", nullable = false, length = 255)
    private String konu;

    @Column(name = "aciklama", nullable = false, columnDefinition = "TEXT")
    private String aciklama;

    @Column(name = "olay_tarihi")
    private LocalDateTime olayTarihi;

    @Column(name = "basvuru_tarihi", nullable = false)
    private LocalDateTime basvuruTarihi;

    @Column(name = "oncelik", length = 50)
    private String oncelik;

    @Column(name = "gizli_mi", nullable = false)
    private boolean gizliMi = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "olusturan_kullanici_id")
    private Kullanici olusturanKullanici;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guncelleyen_kullanici_id")
    private Kullanici guncelleyenKullanici;
}
