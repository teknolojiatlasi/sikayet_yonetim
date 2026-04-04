package com.company.sikayet.security.service;

import com.company.sikayet.kullanici.entity.Kullanici;
import com.company.sikayet.kullanici.repository.KullaniciRepository;
import com.company.sikayet.security.model.SecurityUserDetails;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kullanici kullanici = kullaniciRepository.findByKullaniciAdi(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanici bulunamadi"));

        return new SecurityUserDetails(
                kullanici.getId(),
                kullanici.getKullaniciAdi(),
                kullanici.getSifreHash(),
                kullanici.isActive(),
                kullanici.getKullaniciRolleri().stream()
                        .flatMap(kullaniciRol -> Stream.concat(
                                Stream.of(new SimpleGrantedAuthority(kullaniciRol.getRol().getKod())),
                                kullaniciRol.getRol().getRolYetkileri().stream()
                                        .map(rolYetki -> new SimpleGrantedAuthority(rolYetki.getYetki().getKod()))))
                        .distinct()
                        .toList());
    }
}
