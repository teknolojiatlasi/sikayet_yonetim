package com.company.sikayet.kullanici.entity;

import com.company.sikayet.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kullanicilar")
public class Kullanici extends BaseEntity {

    @Column(name = "kullanici_adi", nullable = false, unique = true, length = 100)
    private String kullaniciAdi;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "sifre_hash", nullable = false, length = 255)
    private String sifreHash;

    @Column(name = "ad", nullable = false, length = 100)
    private String ad;

    @Column(name = "soyad", nullable = false, length = 100)
    private String soyad;

    @OneToMany(mappedBy = "kullanici")
    private Set<KullaniciRol> kullaniciRolleri = new HashSet<>();
}
