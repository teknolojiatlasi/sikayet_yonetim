package com.company.sikayet.kullanici.service.impl;

import com.company.sikayet.exception.ResourceNotFoundException;
import com.company.sikayet.kullanici.dto.KullaniciCreateRequest;
import com.company.sikayet.kullanici.dto.KullaniciResponse;
import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.kullanici.entity.KullaniciRol;
import com.company.sikayet.kullanici.repository.KullaniciRepository;
import com.company.sikayet.kullanici.service.KullaniciService;
import com.company.sikayet.role.entity.Rol;
import com.company.sikayet.role.entity.RolYetki;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KullaniciServiceImpl implements KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USER_VIEW')")
    public List<KullaniciResponse> findAll() {
        return kullaniciRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public KullaniciResponse create(KullaniciCreateRequest request) {
        Kullanici kullanici = new Kullanici();
        kullanici.setKullaniciAdi(request.kullaniciAdi());
        kullanici.setEmail(request.email());
        kullanici.setSifreHash(passwordEncoder.encode(request.sifre()));
        kullanici.setAd(request.ad());
        kullanici.setSoyad(request.soyad());

        Kullanici saved = kullaniciRepository.save(kullanici);
        Rol operatorRole = findRoleByKod("ROLE_OPERATOR");
        KullaniciRol kullaniciRol = new KullaniciRol();
        kullaniciRol.setKullanici(saved);
        kullaniciRol.setRol(operatorRole);
        entityManager.persist(kullaniciRol);

        return toResponse(saved);
    }

    private Rol findRoleByKod(String kod) {
        TypedQuery<Rol> query = entityManager.createQuery("select r from Rol r where r.kod = :kod", Rol.class);
        query.setParameter("kod", kod);
        return query.getResultStream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Rol bulunamadi."));
    }

    private KullaniciResponse toResponse(Kullanici entity) {
        return new KullaniciResponse(
                entity.getId(),
                entity.getKullaniciAdi(),
                entity.getEmail(),
                entity.getAd(),
                entity.getSoyad(),
                entity.isActive(),
                entity.getKullaniciRolleri().stream().map(KullaniciRol::getRol).map(Rol::getKod).toList());
    }
}
