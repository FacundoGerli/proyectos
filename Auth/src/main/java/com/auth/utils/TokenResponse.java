package com.auth.utils;

import lombok.Builder;
import lombok.Data;

@Builder
public record TokenResponse(
        String token,
        String refreshToken) {
}
