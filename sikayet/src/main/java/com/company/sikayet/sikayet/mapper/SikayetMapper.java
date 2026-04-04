package com.company.sikayet.sikayet.mapper;

import com.company.sikayet.sikayet.dto.SikayetDetailResponse;
import com.company.sikayet.sikayet.dto.SikayetListResponse;
import com.company.sikayet.sikayet.entity.Sikayet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SikayetMapper {

    @Mapping(target = "kisiAdSoyad", expression = "java(entity.getKisi().getAd() + \" \" + entity.getKisi().getSoyad())")
    @Mapping(target = "sikayetTuru", source = "sikayetTuru.ad")
    @Mapping(target = "durum", source = "sikayetDurumu.ad")
    SikayetListResponse toListResponse(Sikayet entity);

    @Mapping(target = "kisiAdSoyad", expression = "java(entity.getKisi().getAd() + \" \" + entity.getKisi().getSoyad())")
    @Mapping(target = "sikayetTuru", source = "sikayetTuru.ad")
    @Mapping(target = "durum", source = "sikayetDurumu.ad")
    SikayetDetailResponse toDetailResponse(Sikayet entity);
}
