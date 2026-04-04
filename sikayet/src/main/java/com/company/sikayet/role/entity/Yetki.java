package com.company.sikayet.role.entity;

import com.company.sikayet.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "yetkiler")
public class Yetki extends BaseEntity {

    @Column(name = "kod", nullable = false, unique = true, length = 100)
    private String kod;

    @Column(name = "aciklama", length = 255)
    private String aciklama;
}
