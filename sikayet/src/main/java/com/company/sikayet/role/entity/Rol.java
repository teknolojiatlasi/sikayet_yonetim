package com.company.sikayet.role.entity;

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
@Table(name = "roller")
public class Rol extends BaseEntity {

    @Column(name = "kod", nullable = false, unique = true, length = 100)
    private String kod;

    @Column(name = "ad", nullable = false, length = 100)
    private String ad;

    @OneToMany(mappedBy = "rol")
    private Set<RolYetki> rolYetkileri = new HashSet<>();
}
