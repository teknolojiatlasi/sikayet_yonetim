package com.company.sikayet.kisi.entity;

import com.company.sikayet.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kisiler")
public class Kisi extends BaseEntity {

    @Column(name = "ad", nullable = false, length = 100)
    private String ad;

    @Column(name = "soyad", nullable = false, length = 100)
    private String soyad;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "tc_kimlik_no", length = 11)
    private String tcKimlikNo;

    @Column(name = "adres", columnDefinition = "TEXT")
    private String adres;

    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @Column(name = "cinsiyet", length = 20)
    private String cinsiyet;

    @Column(name = "kisi_tipi", nullable = false, length = 30)
    private String kisiTipi = "GERCEK";

    @Column(name = "firma_adi", length = 200)
    private String firmaAdi;

    @Column(name = "vergi_no", length = 50)
    private String vergiNo;
}
