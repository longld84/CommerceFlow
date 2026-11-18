package com.longld.commerceflow.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailRequestDTO {
    @NotBlank
    private String token;
}
