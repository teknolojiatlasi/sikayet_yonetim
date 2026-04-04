package com.company.sikayet.kullanici.repository;

import com.company.sikayet.kullanici.entity.Kullanici;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    @EntityGraph(attributePaths = {"kullaniciRolleri", "kullaniciRolleri.rol", "kullaniciRolleri.rol.rolYetkileri", "kullaniciRolleri.rol.rolYetkileri.yetki"})
    Optional<Kullanici> findByKullaniciAdi(String kullaniciAdi);
}
