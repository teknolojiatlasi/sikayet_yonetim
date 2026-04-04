package com.company.sikayet.sikayet.entity;

import com.company.sikayet.common.base.BaseEntity;
import com.company.sikayet.kullanici.entity.Kullanici;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sikayet_sonuclari")
public class SikayetSonucu extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sikayet_id", nullable = false, unique = true)
    private Sikayet sikayet;

    @Column(name = "sonuc_metin", nullable = false, columnDefinition = "TEXT")
    private String sonucMetin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sonuclandiran_kullanici_id")
    private Kullanici sonuclandiranKullanici;

    @Column(name = "sonuclanma_tarihi", nullable = false)
    private LocalDateTime sonuclanmaTarihi;
}
