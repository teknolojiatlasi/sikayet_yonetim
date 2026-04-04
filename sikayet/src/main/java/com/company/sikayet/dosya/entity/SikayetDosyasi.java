package com.company.sikayet.dosya.entity;

import com.company.sikayet.common.base.BaseEntity;
import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.sikayet.entity.Sikayet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sikayet_dosyalari")
public class SikayetDosyasi extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_id", nullable = false)
    private Sikayet sikayet;

    @Column(name = "orijinal_dosya_adi", nullable = false, length = 255)
    private String orijinalDosyaAdi;

    @Column(name = "sistem_dosya_adi", nullable = false, length = 255)
    private String sistemDosyaAdi;

    @Column(name = "dosya_yolu", nullable = false, length = 500)
    private String dosyaYolu;

    @Column(name = "mime_type", nullable = false, length = 150)
    private String mimeType;

    @Column(name = "dosya_boyutu", nullable = false)
    private long dosyaBoyutu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yukleyen_kullanici_id")
    private Kullanici yukleyenKullanici;
}
