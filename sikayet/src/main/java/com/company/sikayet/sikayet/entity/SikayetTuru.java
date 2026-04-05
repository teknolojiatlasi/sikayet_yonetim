package com.company.sikayet.sikayet.entity;

import com.company.sikayet.common.base.BaseEntity;
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
@Table(name = "sikayet_turleri")
public class SikayetTuru extends BaseEntity {

    @Column(name = "kod", nullable = false, unique = true, length = 50)
    private String kod;

    @Column(name = "ad", nullable = false, length = 100)
    private String ad;

    @Column(name = "aciklama", length = 255)
    private String aciklama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SikayetTuru parent;

    @Column(name = "sira_no", nullable = false)
    private Integer siraNo = 0;
}
