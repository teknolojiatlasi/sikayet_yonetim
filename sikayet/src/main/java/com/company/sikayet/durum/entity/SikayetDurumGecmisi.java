package com.company.sikayet.durum.entity;

import com.company.sikayet.common.base.BaseEntity;
import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.sikayet.entity.Sikayet;
import com.company.sikayet.sikayet.entity.SikayetDurumu;
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
@Table(name = "sikayet_durum_gecmisi")
public class SikayetDurumGecmisi extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_id", nullable = false)
    private Sikayet sikayet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eski_durum_id")
    private SikayetDurumu eskiDurum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "yeni_durum_id", nullable = false)
    private SikayetDurumu yeniDurum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degistiren_kullanici_id")
    private Kullanici degistirenKullanici;

    @Column(name = "degisim_tarihi", nullable = false)
    private LocalDateTime degisimTarihi;

    @Column(name = "aciklama", length = 255)
    private String aciklama;
}
