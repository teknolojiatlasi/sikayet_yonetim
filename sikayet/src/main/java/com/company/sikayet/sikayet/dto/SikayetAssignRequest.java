package com.company.sikayet.sikayet.dto;

import jakarta.validation.constraints.NotNull;

public record SikayetAssignRequest(@NotNull(message = "Atanan kullanici zorunludur.") Long kullaniciId) {
}
