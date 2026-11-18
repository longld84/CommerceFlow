package com.longld.commerceflow.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.longld.commerceflow.dto.user.response.UserResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponseDTO {
    private final String accessToken;
    private final String refreshToken;

    @Builder.Default
    private final String tokenType = "Bearer";

    private final long expiresInSeconds;
    private final boolean emailVerified;

    @JsonProperty("user")
    private final UserResponseDTO user;
}
